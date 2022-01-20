package br.com.carlosjunior.msemail.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import br.com.carlosjunior.msemail.entities.EmailEntity;
import br.com.carlosjunior.msemail.entities.enums.StatusEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailDto {

	@NotBlank
	private String ownerRef;

	@NotBlank
	private String emailTo;

	@NotBlank
	private String emailFrom;

	@NotBlank
	private String subject;

	@NotBlank
	private String text;

	private LocalDateTime sendDataEmail;
	private StatusEmail statusEmail;
	
	public EmailDto(EmailEntity email) {
		this.ownerRef = email.getOwnerRef();
		this.emailTo = email.getEmailTo();
		this.emailFrom = email.getEmailFrom();
		this.subject = email.getSubject();
		this.text = email.getText();
		
		this.statusEmail = email.getStatusEmail();
		this.sendDataEmail = email.getSendDataEmail();
	}

}
