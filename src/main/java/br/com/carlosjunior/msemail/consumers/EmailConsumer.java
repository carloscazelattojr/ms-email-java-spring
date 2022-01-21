package br.com.carlosjunior.msemail.consumers;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.carlosjunior.msemail.dto.EmailDto;
import br.com.carlosjunior.msemail.entities.EmailEntity;
import br.com.carlosjunior.msemail.entities.enums.StatusEmail;
import br.com.carlosjunior.msemail.services.EmailService;

@Component
public class EmailConsumer {

	@Autowired
	EmailService service;

	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDto emailDto) {
		
		EmailEntity novoEmail = new EmailEntity();
		novoEmail.setEmailTo(emailDto.getEmailTo());
		novoEmail.setEmailFrom(emailDto.getEmailFrom());
		novoEmail.setOwnerRef(emailDto.getOwnerRef());
		novoEmail.setSubject(emailDto.getSubject());
		novoEmail.setText(emailDto.getText());
		novoEmail.setStatusEmail(StatusEmail.SENT);
		novoEmail.setSendDataEmail(LocalDateTime.now());
		
		//BeanUtils.copyProperties(emailDto, emailEntity);
		
		service.sendingEmail(new EmailDto(novoEmail));
		System.out.println("Email Status: " + novoEmail.getStatusEmail().toString());
	}

}
