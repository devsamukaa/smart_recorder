package br.com.gotech.smartrecorder.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class GoTechMail {

    @Autowired
    private JavaMailSender emailSender;

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("gotechteamm@gmail.com");
        mailSender.setPassword("gotech@123");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public boolean sendMail(String message, String to, String subject) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("noreply@gotech.com");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        emailSender.send(mail);

        return true;
    }

}
