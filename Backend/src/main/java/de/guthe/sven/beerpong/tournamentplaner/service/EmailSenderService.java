package de.guthe.sven.beerpong.tournamentplaner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

	private final JavaMailSender javaMailSender;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param javaMailSender the mail-sender objects to send mails to the users
	 */
	@Autowired
	public EmailSenderService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * Send an email async to the user
	 * @param email a Simple Mail Message
	 */
	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}

}
