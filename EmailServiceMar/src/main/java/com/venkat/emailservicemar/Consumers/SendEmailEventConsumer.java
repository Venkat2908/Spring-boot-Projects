package com.venkat.emailservicemar.Consumers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venkat.emailservicemar.Dtos.SendEmailDto;
import com.venkat.emailservicemar.Utils.EmailUtil;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailEventConsumer {

    private  ObjectMapper objectMapper;

    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics ="signup",groupId ="EmailService")
    public void handleSendEmailEvent(String message) throws JsonProcessingException {

        SendEmailDto sendemail = objectMapper.readValue(
                message,SendEmailDto.class

        );

        String to =sendemail.getTo();
        String subject = sendemail.getSubject();
        String body = sendemail.getBody();


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("venkatramanv0899@gmail.com", "ulgciqfdouswipvc");
            }
        };
        Session session = Session.getInstance(props, auth);



        EmailUtil.sendEmail(session, to,subject, body);

    }



    }

