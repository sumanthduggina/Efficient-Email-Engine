package com.sumanth.email_campaign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sumanth.email_campaign.dto.User;

@Service
public class EmailService {
	@Autowired
	private  JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String fromEmail;
	
    public void sendEmail(String toEmail, String firstName,String lastName) {
	    
    
        try {
        	SimpleMailMessage message = new SimpleMailMessage();
        	message.setFrom(fromEmail);
        	message.setTo(toEmail);
            message.setSubject(" Exclusive Invite: Join Our Spring Boot Campaign!");
            message.setText(
            		"Hi " + firstName + " " +lastName+",\n\n" +
                    	    "We’re excited to let you know about our new Spring Boot campaign! 🎉\n\n" +
                    	    "Here’s what you’ll get:\n" +
                    	    "✔ Hands-on tutorials to boost your skills\n" +
                    	    "✔ Best practices for building scalable applications\n" +
                    	    "✔ Access to community-driven projects\n\n" +
                    	    "Don’t miss out — this is your chance to stay ahead in modern application development.\n\n" +
                    	    "👉 Click here to learn more: https://example.com/spring-boot-campaign\n\n" +
                    	    "Best regards,\n" +
                    	    "The Spring Boot Campaign Team");
            mailSender.send(message);
            System.out.println("Sent email to: " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email to: " + toEmail + " | " + e.getMessage());
        }
    }
}
