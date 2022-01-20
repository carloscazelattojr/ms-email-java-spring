package br.com.carlosjunior.msemail.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosjunior.msemail.dto.EmailDto;
import br.com.carlosjunior.msemail.services.EmailService;

@RestController
public class EmailConntroller {

	@Autowired
	private EmailService service;

	@PostMapping("/sending-email")
	public ResponseEntity<EmailDto> sendingEmail(@Valid @RequestBody EmailDto email) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.sendingEmail(email));
	}
}
