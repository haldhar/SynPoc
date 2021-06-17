package com.order.orderdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.orderdemo.controller.model.AuthenticationRequest;
import com.order.orderdemo.controller.model.AuthenticationResponse;
import com.order.orderdemo.service.AuthenticationService;

@CrossOrigin
@RestController
public class AuthenticationController {

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping(path = "/hello")
	public ResponseEntity<String> authenticate() {
		return new ResponseEntity<>(String.format("Hello %s!", "hello"), HttpStatus.OK);
	}

	@GetMapping(path = "/hello1")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	/*
	 * @GetMapping(path = "/authenticate") public String
	 * authenticate(@RequestParam(value = "name", defaultValue = "haldhar") String
	 * name,@RequestParam(value = "password", defaultValue = "admin") String
	 * password) { AuthenticationRequest authenticationRequest = new
	 * AuthenticationRequest(); authenticationRequest.setUserName(name);
	 * authenticationRequest.setPassword(password); var authenticationResponse =
	 * authenticationService.authenticate(authenticationRequest); return
	 * String.format("Hello %s!", authenticationResponse.getJwtToken()); }
	 */

}
