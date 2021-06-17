package com.order.orderdemo.service;

import com.order.orderdemo.controller.model.AuthenticationRequest;
import com.order.orderdemo.controller.model.AuthenticationResponse;

/**
 * @author Haldhar.Seth
 *
 */
public interface AuthenticationService {

	/**
	 * This method is responsible for authentication functionality
	 * 
	 * @param authenticationRequest
	 * @return AuthenticationResponse
	 *
	 */
	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

}
