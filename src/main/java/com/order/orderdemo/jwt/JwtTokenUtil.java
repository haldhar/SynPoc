package com.order.orderdemo.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	private static final long serialVersionUID = -3301605591108950415L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_KEY_ROLES = "Roles";

	@Value("${jwt.secret}")
	private String fileName;

	private String publicKey;

	@Value("${jwt.expiration}")
	private Long expiration;

	/**
	 * Generate token.
	 * 
	 * @return
	 */
	public String generateToken(String userName, UserDetails userDetails) {

		final String authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userName);
		claims.put(CLAIM_KEY_CREATED, new Date());
		claims.put(CLAIM_KEY_ROLES, authorities);
		return generateToken(claims);
	}

	/**
	 * Validate Token.
	 * 
	 * @param token
	 * @return
	 */

	public Boolean validateToken(String token) {
		try {
			final String username = getUsernameFromToken(token);
			return (username != null && !isTokenExpired(token));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Read file and assign for public key.
	 */
	private void readPublicKey() {
		if (publicKey == null) {
			var fileContent = new StringBuilder();
			try {
				
				var classPathResource = new ClassPathResource(fileName);
				var inputStream = classPathResource.getInputStream();
				var br = new BufferedReader(new InputStreamReader(inputStream));
				String currentLine;
				while ((currentLine = br.readLine()) != null) {
					fileContent.append(currentLine);
				}
				br.close();

			} catch (IOException e) {
				logger.error("IOException in getFileContent", e);
			}
			publicKey = fileContent.toString();
		}
	}

	/**
	 * Return username from token.
	 * 
	 * @param token
	 * @return
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final var claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Get expiration date from generated token.
	 * 
	 * @param token
	 * @return
	 */

	private Date getExpirationDateFromToken(String token) {
		Date expire;
		try {
			final var claims = getClaimsFromToken(token);
			expire = claims.getExpiration();
		} catch (Exception e) {
			expire = null;
		}
		return expire;
	}

	private Claims getClaimsFromToken(String token) {
		readPublicKey();
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * Generate expiry date of token by adding expiration property.
	 * 
	 * @return
	 */

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * Return true if token expired.
	 * 
	 * @param token
	 * @return
	 */

	private Boolean isTokenExpired(String token) {
		final var expire = getExpirationDateFromToken(token);
		return expire.before(new Date());
	}

	/**
	 * Generate token with expiry date and secrete key.
	 * 
	 * @param claims
	 * @return
	 */
	private String generateToken(Map<String, Object> claims) {
		readPublicKey();
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, publicKey).compact();
	}

}
