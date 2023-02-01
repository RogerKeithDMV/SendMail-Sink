package com.cloudgen.n3xgen.service;

import java.util.Properties;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.cloudgen.n3xgen.bean.MailDetail;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

@Service
@Log4j2
public class MailServiceImp implements MailService {
    @Override
    public String sendMail(String payload, MailDetail mailDetail) {
        String response = "";
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", mailDetail.getHost());
            props.put("mail.smtp.port", "587");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailDetail.getUsername(), mailDetail.getPassword());
                }
            };

            Session session = Session.getInstance(props, auth);

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mailDetail.getUsername()));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailDetail.getRecipient()));
                message.setSubject(mailDetail.getSubject());
                message.setText(payload);
                Multipart multipart = new MimeMultipart();

				/*Documents
				if(mailDetail.getDocuments()!=null) {
					for(Documents documment:mailDetail.getDocuments()) {
						MimeBodyPart attachment= new MimeBodyPart();
						attachment.setText(documment.getDocumentString());
						attachment.setFileName(documment.getDocumentName());

						multipart.addBodyPart(attachment);
					}
					message.setContent(multipart);
				}
				//Documents*/

                MimeBodyPart textBody = new MimeBodyPart();
                byte[] decoder = payload.getBytes();
                DataSource dataSource = new ByteArrayDataSource(decoder, "text/html");
                textBody.setDataHandler(new DataHandler(dataSource));
                multipart.addBodyPart(textBody);

                Transport.send(message);
                log.debug("Email Message Sent Successfully.");
                response = "Email Message Sent Successfully.";
            } catch (MessagingException e) {
                response = "Error while sending the e-mail: " + e;
                log.debug("Error while sending the e-mail: " + e);
                throw new RuntimeException(e);
            }

            return response;
        } catch (Exception e) {
            log.debug("Error while sending e-mail: " + e);
            return "Error while Sending email: " + e;
        }
    }
}