package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {
    static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;
    public void send(String email,String url){
        try{
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setTo("s095438@gmail.com");
//            message.setSubject("subject");
//            message.setText("text");
//            emailSender.send(message);
//
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(url);
            helper.setSubject("Confirm your email --OilCloud");
            helper.setFrom("noreply@oilcloud.com");
            helper.setTo(email);
            mailSender.send(mimeMessage);
        }catch(Exception e){
            LOGGER.error("fail to send email",e);
        }
    }
}
