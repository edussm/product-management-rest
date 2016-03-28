package br.fpu.rest.hello.domain;

import java.io.Serializable;

public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cnpj;
	private String endereco;
	private String email;
	private String telefone;

	public Provider() {
	}

	public Provider(Long id, String nome, String cnpj, String endereco, String email, String telefone) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.telefone = telefone;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fornecedor [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cnpj=");
		builder.append(cnpj);
		builder.append(", endereco=");
		builder.append(endereco);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append("]");
		return builder.toString();
	}
	
	

}
