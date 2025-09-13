package com.sumanth.email_campaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

public class BulkEmailSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkEmailSenderApplication.class, args);
	}

}
