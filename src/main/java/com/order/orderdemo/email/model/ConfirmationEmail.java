package com.order.orderdemo.email.model;

public class ConfirmationEmail {
	private String to;
	private String from;
	private String cc;
	private String bcc;
	private String replyTo;
	private String subject;
	private String body;

	public ConfirmationEmail(String to, String from, String cc, String bcc, String replyTo, String subject,String body) {
		this.to = to;
		this.from = from;
		this.cc = cc;
		this.bcc = bcc;
		this.replyTo = replyTo;
		this.subject = subject;
		this.body = body;
	}

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
}
