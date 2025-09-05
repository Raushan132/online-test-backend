package com.test.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.test.email.CustomMsg;
import com.test.email.service.IEmailService;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String emailName;

    @Value("${backend-baseUrl}")
    private static String baseUrl;

    @Override
    public boolean emailSend(String receiver, String subject, String token,Integer id) {
        boolean isSend = false;

        try {
            MimeMessage mimeMsg = sender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
            helper.setFrom(emailName); // your sender email
            helper.setTo(receiver);
            helper.setSubject(subject);
            
            // Generate HTML email body with token
            String body = CustomMsg.emailBody(token,id,baseUrl);
            helper.setText(body, true); // true => HTML content

            // Send mail
            sender.send(mimeMsg);
            isSend = true;
            log.info("Email sent successfully to {}", receiver);
        } catch (Exception e) {
            log.error("Email send failed for {}: {}", receiver, e.getMessage());
        }

        return isSend;
    }
}
