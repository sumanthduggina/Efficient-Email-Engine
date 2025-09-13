package com.sumanth.email_campaign.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumanth.email_campaign.dto.User;
import com.sumanth.email_campaign.service.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {
	
	private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }
	
    @PostMapping("/sendUsers")
    public String sendEmailsForUsers(@RequestBody List<User> users) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // 10 threads
        for (User user : users) {
            executor.submit(() -> emailService.sendEmail(
                    user.getEmail(),
                    user.getFirst_name(),
                    user.getLast_name()
            ));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.MINUTES); // wait up to 5 minutes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Execution interrupted.";
        }
        return "All emails sent in parallel: " + users.size();
    }

}
