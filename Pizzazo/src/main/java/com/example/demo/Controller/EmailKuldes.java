package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailKuldes {
    @Autowired
    private JavaMailSender emailKuldo;

    public void uzenetKikuldese(String cel, String body, String targy){
        SimpleMailMessage uzenet = new SimpleMailMessage();

        uzenet.setFrom("pizzaneked001@gmail.com");
        uzenet.setTo(cel);
        uzenet.setText(body);
        uzenet.setSubject(targy);
        emailKuldo.send(uzenet);
    }
}
