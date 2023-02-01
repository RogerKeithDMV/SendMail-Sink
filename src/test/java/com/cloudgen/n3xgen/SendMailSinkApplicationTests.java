package com.cloudgen.n3xgen;

import com.cloudgen.n3xgen.service.MailServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cloudgen.n3xgen.bean.MailDetail;

@SpringBootTest
class SendMailSinkApplicationTests {

	MailServiceImp serviceImpl=new MailServiceImp();

	@Test
	void emailSentCorrectly() {
		String playload="This is the body";
		MailDetail detail=new MailDetail("lastbrick@gmail.com", "Hello there!!!", "smtp.gmail.com", "lastbrick@gmail.com", "rvxnlsfttjyasqar");
		String responseCorrectly=serviceImpl.sendMail(playload, detail);
		System.out.println("Respuesta correcta: "+responseCorrectly);
		String resposeAwaited="Email Message Sent Successfully";
		Assertions.assertTrue((responseCorrectly.toUpperCase()).contains(resposeAwaited.toUpperCase()));
	}

	@Test
	void emailSentWithWrongPassword() {
		String playload="This is the body";
		MailDetail detail=new MailDetail("lastbrick@gmail.com", "Hello there!!!", "smtp.gmail.com", "lastbrick@gmail.com", "rvxnlsfttjyasqar");
		String wrongResponse=serviceImpl.sendMail(playload, detail);
		System.out.println("Respuesta incorrecta: "+wrongResponse);
		Assertions.assertTrue(wrongResponse.toUpperCase().contains("Error".toUpperCase()));
	}
	
	@Test
	void emailSentWithWrongEMail() {
		String playload="This is the body";
		MailDetail detail=new MailDetail("lastbrick@gmail.com", "Hello there!!!", "smtp.gmail.com", "lastbric@gmail.com", "rvxnlsfttjdasqar");
		String wrongResponse=serviceImpl.sendMail(playload, detail);
		System.out.println("Respuesta incorrecta: "+wrongResponse);
		Assertions.assertTrue(wrongResponse.toUpperCase().contains("Error".toUpperCase()));
	}
	
	@Test
	void emailSentWithWrongHost() {
		String playload="This is the body";
		MailDetail detail=new MailDetail("lastbrick@gmail.com", "Hello there!!!", "smtp.gmail.con", "lastbrick@gmail.com", "rvxnlsfttjyasqar");
		String wrongResponse=serviceImpl.sendMail(playload, detail);
		System.out.println("Respuesta incorrecta: "+wrongResponse);
		Assertions.assertTrue(wrongResponse.toUpperCase().contains("Error".toUpperCase()));
	}
}
