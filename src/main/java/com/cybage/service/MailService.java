package com.cybage.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cybage.exception.CustomMailException;
import com.cybage.pojo.NotificationEmailDTO;

@Service

public class MailService {

	@Value("${spring.mail.username}")
	private String host;

	Logger log = LogManager.getLogger("MailService.class");
	@Autowired
	private JavaMailSender javaMailSender;
//	@Autowired
//	private MailContentBuilder mailContentBuilder;

	@Async
	public void sendMail(NotificationEmailDTO notificationEmail) {

		MimeMessagePreparator messagePreparator = mimeMessage -> {

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			System.out.println("in java mail sender");
			messageHelper.setFrom(host);
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(notificationEmail.getBody());
		};

		try {

			javaMailSender.send(messagePreparator);
			System.out.println("Success");
			log.info("OTP sent to the " + notificationEmail.getRecipient());

		} catch (MailException e) {
			// TODO: handle exception
			System.out.println("Failed");
			throw new CustomMailException("Exception occured when sending mail to " + notificationEmail.getRecipient());
		}
	}

}
