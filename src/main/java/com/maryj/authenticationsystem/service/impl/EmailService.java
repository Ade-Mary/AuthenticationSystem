package com.maryj.authenticationsystem.service.impl;

import com.maryj.authenticationsystem.dto.EmailDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private  String emailSender;

    public  void sendEmail(EmailDetail emailDetail) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailSender);
            simpleMailMessage.setTo(emailDetail.getRecipient());
            simpleMailMessage.setText(emailDetail.getMessageBody());
            simpleMailMessage.setSubject(emailDetail.getSubject());

            javaMailSender.send(simpleMailMessage);
            log.info("Message sent to: {}", emailDetail.getRecipient());
            log.info("Message sender: {}", emailSender);

        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }


}
