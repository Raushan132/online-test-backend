package com.test.email.service;

public interface IEmailService {

	public boolean emailSend(String receiver, String subject, String body);
}
