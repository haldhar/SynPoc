package com.order.orderdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.order.orderdemo.controller.model.AuthenticationRequest;
import com.order.orderdemo.controller.model.AuthenticationResponse;
import com.order.orderdemo.dao.UserDetailsRepository;
import com.order.orderdemo.dao.entity.User;
import com.order.orderdemo.jwt.JwtTokenGenerator;
import com.order.orderdemo.jwt.JwtUserDetailsServiceImpl;
import com.order.orderdemo.model.ResultCode;
import com.order.orderdemo.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	@Autowired
	JwtTokenGenerator jwtTokenGenerator;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		var user = new User();
		user.setUsername(authenticationRequest.getUserName());
		user.setPassword(authenticationRequest.getPassword());
		var authenticationResponse = new AuthenticationResponse();
		try {
			var authenticatedUser = userDetailsRepository.authenticate(user.getUsername(), user.getPassword());

			authenticationResponse.setUsername(authenticatedUser.getUsername());

			authenticationResponse.setResultStatus(ResultCode.SUCCESS.getStatus());
			authenticationResponse.setResultCode(ResultCode.SUCCESS.getCode());
			authenticationResponse.setMessage(ResultCode.SUCCESS.getMessage());
			var userDetails = jwtUserDetailsServiceImpl.loadUserByUsername(user.getUsername());
			String token = jwtTokenGenerator.generateToken(authenticationRequest.getUserName(), userDetails);
			authenticationResponse.setJwtToken(token);

		} catch (EmptyResultDataAccessException emptyResultDataAccessException) {
			authenticationResponse.setResultStatus(ResultCode.AUTHENTICATION_FAILURE.getStatus());
			authenticationResponse.setResultCode(ResultCode.AUTHENTICATION_FAILURE.getCode());
			authenticationResponse.setMessage(ResultCode.AUTHENTICATION_FAILURE.getMessage());
		}
		return authenticationResponse;
	}

}
