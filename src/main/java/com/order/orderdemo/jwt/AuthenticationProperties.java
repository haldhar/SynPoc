package com.order.orderdemo.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProperties {
	
	@Value("#{'${jwt.security.permit}'.split(',')}")
	private final List<String> permit = new ArrayList<>();

	public List<String> getPermit() {
		return this.permit;
	}

}
