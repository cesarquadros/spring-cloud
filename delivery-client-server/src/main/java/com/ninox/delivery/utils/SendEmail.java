package com.ninox.delivery.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

import com.ninox.delivery.document.Client;

@Component
public class SendEmail {

	private String HOST_NAME = "smtp.googlemail.com";
	private Integer SMTP_PORT = 587;

	public String simpleEmail(Client client) {

		try {
			Email email = new SimpleEmail();
			email.setHostName(HOST_NAME);
			email.setSmtpPort(SMTP_PORT);
			email.setAuthentication("ninoreggae", "senha");
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);
			email.setFrom("support@springcloud.com");
			email.setSubject("TestMail");
			email.setMsg("Teste envio de email usando CommonsEmail");
			email.addTo(client.getEmail());
			email.send();
			
			return "Sucesso";
		} catch (EmailException e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
