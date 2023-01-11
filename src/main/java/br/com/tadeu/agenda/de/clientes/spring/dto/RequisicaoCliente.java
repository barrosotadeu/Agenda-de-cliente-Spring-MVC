package br.com.tadeu.agenda.de.clientes.spring.dto;

import br.com.tadeu.agenda.de.clientes.spring.model.Cliente;
import jakarta.validation.constraints.NotBlank;

public class RequisicaoCliente {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	@NotBlank
	private String telefone;
	@NotBlank
	private String email;
	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String complemento;
	@NotBlank
	private String bairro;
	@NotBlank
	private String localidade;
	@NotBlank
	private String uf;
	
	
	
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
	
	
	
	
	
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
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
