package com.order.orderdemo.jwt;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.order.orderdemo.dao.UserDetailsRepository;
import com.order.orderdemo.dao.entity.User;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) {
		var user = userDetailsRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set getAuthority(User user) {
		Set authorities = new HashSet();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
		return authorities;
	}

}
