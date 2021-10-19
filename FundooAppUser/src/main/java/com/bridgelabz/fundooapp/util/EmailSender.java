package com.bridgelabz.fundooapp.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundooapp.exception.FunDoNotesCustomException;



@Component
public class EmailSender {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public Boolean isEmailSent(String to, String from, String subject, String body) {
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(to);
			mail.setFrom(from);
			mail.setSubject(subject);
			mail.setText(body);
			javaMailSender.send(mail);
			return true;
		} 
		
		catch (Exception e) {
			throw new FunDoNotesCustomException(HttpStatus.BAD_REQUEST,"EMail is Not Authenticate");
		}
		
	}
}
