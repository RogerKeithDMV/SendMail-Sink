package com.cloudgen.n3xgen.stream;

import java.util.function.Consumer;

import com.cloudgen.n3xgen.service.MailService;
import com.cloudgen.n3xgen.service.MailServiceImp;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.cloudgen.n3xgen.bean.MailDetail;

import lombok.extern.log4j.Log4j2;

@Log4j2
@EnableConfigurationProperties({MailDetail.class})
@Configuration
public class MailConfiguration {
    @Bean
    public Consumer<Message<?>> sendMail(MailDetail mailDetail) {
        return payload -> {

            log.info("Starting sending the email");
            MailService mailSender = new MailServiceImp();
            String eMailResponse = mailSender.sendMail(new String((byte[]) payload.getPayload()), mailDetail);
            log.info("The email has the next response: " + eMailResponse);
            //new String((byte[])payload.getPayload()), eMailResponse);
        };
    }
}