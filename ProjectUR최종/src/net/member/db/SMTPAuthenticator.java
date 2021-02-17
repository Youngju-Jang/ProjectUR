package net.member.db;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {


	protected PasswordAuthentication getPasswordAuthentication() {
		String id = "rmedictest";
		String pass = "test_medic!";
		return new PasswordAuthentication(id, pass);
	}
}
