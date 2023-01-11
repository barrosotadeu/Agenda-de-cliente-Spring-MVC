package br.com.tadeu.agenda.de.clientes.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;	;
	private Boolean vendeu = false;
	@ManyToOne
	private Endereco endereco;
	
	
//	private String cep;
//	private String logradouro;
//	private String complemento;
//	private String bairro;
//	private String localidade;
//	private String uf;
		
	
	
	
//	public String getCep() {
//		return cep;
//	}
//
//	public void setCep(String cep) {
//		this.cep = cep;
//	}
//
//	public String getLogradouro() {
//		return logradouro;
//	}
//
//	public void setLogradouro(String logradouro) {
//		this.logradouro = logradouro;
//	}
//
//	public String getComplemento() {
//		return complemento;
//	}
//
//	public void setComplemento(String complemento) {
//		this.complemento = complemento;
//	}
//
//	public String getBairro() {
//		return bairro;
//	}
//
//	public void setBairro(String bairro) {
//		this.bairro = bairro;
//	}
//
//	public String getLocalidade() {
//		return localidade;
//	}
//
//	public void setLocalidade(String localidade) {
//		this.localidade = localidade;
//	}
//
//	public String getUf() {
//		return uf;
//	}
//
//	public void setUf(String uf) {
//		this.uf = uf;
//	}



	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cliente(String nome, String cpf, String telefone, String email) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		
	}

	public Cliente() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getVendeu() {
		 return vendeu == false? "Não" : "Sim";
		}
	public void setVendeu(Boolean vendeu) {
		this.vendeu = vendeu;
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + id + ", Nome: " + nome;
	}
	
	
}
