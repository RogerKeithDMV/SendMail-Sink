package com.cloudgen.n3xgen.service;

import com.cloudgen.n3xgen.bean.MailDetail;

public interface MailService {
	String sendMail(String payload, MailDetail mailDetail);
}
