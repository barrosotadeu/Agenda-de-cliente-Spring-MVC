package br.com.tadeu.agenda.de.clientes.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tadeu.agenda.de.clientes.spring.model.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

}
