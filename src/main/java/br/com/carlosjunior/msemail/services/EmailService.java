package br.com.carlosjunior.msemail.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.carlosjunior.msemail.dto.EmailDto;
import br.com.carlosjunior.msemail.entities.EmailEntity;
import br.com.carlosjunior.msemail.entities.enums.StatusEmail;
import br.com.carlosjunior.msemail.repositories.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailRepository repository;

	public EmailDto sendingEmail(EmailDto email) {

		SimpleMailMessage msg = new SimpleMailMessage();

		// Montar SEND
		msg.setFrom(email.getEmailFrom());
		msg.setTo(email.getEmailTo());
		msg.setSubject(email.getSubject());
		msg.setText(email.getText());

		// EnviarEmail
		javaMailSender.send(msg);

		//Salvar no banco o email
		EmailEntity novoEmail = new EmailEntity();
		novoEmail.setEmailTo(email.getEmailTo());
		novoEmail.setEmailFrom(email.getEmailFrom());
		novoEmail.setOwnerRef(email.getOwnerRef());
		novoEmail.setSubject(email.getSubject());
		novoEmail.setText(email.getText());
		
		novoEmail.setStatusEmail(StatusEmail.SENT);
		novoEmail.setSendDataEmail(LocalDateTime.now());
		repository.save(novoEmail);
		
		// Converter Entity para Dto e devolver
		return new EmailDto(novoEmail);
	}
}
