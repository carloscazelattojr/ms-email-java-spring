package br.com.carlosjunior.msemail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carlosjunior.msemail.entities.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

}
