package com.order.orderdemo.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.order.orderdemo.email.model.ConfirmationEmail;

public class EmailService {
	@Value("${spreadsheet.email.from}")
	private String emailFrom;

	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	private final JavaMailSender javaMailSender;

	private static final String ENCODING = "UTF-8";

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmail(ConfirmationEmail email) {
		try {
			var message = javaMailSender.createMimeMessage();

			var helper = new MimeMessageHelper(message, true, ENCODING);

			helper.addTo(email.getTo());
			helper.setSubject(email.getSubject());
			helper.setText(email.getBody(), true);

			helper.setFrom(email.getFrom());

			if (email.getCc() != null) {
				helper.addCc(email.getCc());
			}

			if (email.getBcc() != null) {
				helper.addBcc(email.getBcc());
			}

			if (email.getReplyTo() != null) {
				helper.setReplyTo(email.getReplyTo());
			}

			javaMailSender.send(message);

		} catch (Exception ex) {
			logger.error("Error while sending mail {}", ex.getMessage());
		}
	}
}
