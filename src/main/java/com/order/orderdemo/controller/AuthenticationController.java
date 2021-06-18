package com.order.orderdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.orderdemo.controller.model.AuthenticationRequest;
import com.order.orderdemo.controller.model.AuthenticationResponse;
import com.order.orderdemo.service.AuthenticationService;

@CrossOrigin
@RestController
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping(path = "/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		var authenticationResponse = authenticationService.authenticate(authenticationRequest);
		return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
	}

}
