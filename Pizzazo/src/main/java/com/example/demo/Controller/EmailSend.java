package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSend {
    @Autowired
    private JavaMailSender emailSender;

    // send a email with JavaMailSender with the given parameters
    public void send(String destination, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pizzaneked001@gmail.com");
        message.setTo(destination);
        message.setText(body);
        message.setSubject(subject);
        emailSender.send(message);
    }
}
