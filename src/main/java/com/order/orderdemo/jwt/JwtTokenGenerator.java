package com.order.orderdemo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public String generateToken(String userName) {
		// Reload password post-security so we can generate token
		return jwtTokenUtil.generateToken(userName);
	}
}