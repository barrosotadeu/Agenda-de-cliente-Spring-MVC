package br.com.tadeu.agenda.de.clientes.spring.dto;

import br.com.tadeu.agenda.de.clientes.spring.model.Cliente;
import jakarta.validation.constraints.NotBlank;

public class RequisicaoCliente {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	private String telefone;
	@NotBlank
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone + ", Email: " + email;
	}
	
	public void transformaRequisicaoEmCliente(Cliente cliente) {
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);
	
	}
	
	public void transformaClienteEmRequisicao(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
	}
	
}
