package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
public class TestMail {

    @Autowired
    private JavaMailSender emailSender;
    @Test
    void sendToGmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("s095438@gmail.com");
        message.setSubject("subject");
        message.setText("text");
        emailSender.send(message);
    }
}
