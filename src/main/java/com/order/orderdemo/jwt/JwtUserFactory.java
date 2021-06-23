package com.order.orderdemo.jwt;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(String userName) {
		return new JwtUser(userName);
	}
}
