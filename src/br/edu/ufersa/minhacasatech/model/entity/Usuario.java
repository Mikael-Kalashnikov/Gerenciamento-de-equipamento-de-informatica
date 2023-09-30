package br.edu.ufersa.minhacasatech.model.entity;

import java.sql.Date;

public class Usuario {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Endereco endereco;
    private String telefone;
    private Date dataCadastro;

    public Usuario() {}

    public Usuario(String login, String senha) {
	setLogin(login);
	setSenha(senha);
    }
    
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) throws Exception {
	if (id > 0) {
	    this.id = id;
	}
	else {
	    throw new Exception();
	}
    }

    public void setNome(String nome) throws Exception {
	if (nome != null && !nome.isEmpty())
	    this.nome = nome;
	else
	    throw new Exception();
    }

    public String getNome() {
	return this.nome;
    }

    public void setLogin(String login) {
	try {
	    if (login != null && !login.isEmpty())
		this.login = login;
	    else
		throw new Exception();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getLogin() {
	return this.login;
    }

    public void setSenha(String senha) {
	try {
	    if (senha != null && !senha.isEmpty())
		this.senha = senha;
	    else
		throw new Exception();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getSenha() {
	return this.senha;
    }

    public void setEndereco(Endereco endereco) throws Exception {
	if (endereco != null)
	    this.endereco = endereco;
	else
	    throw new Exception();
    }

    public Endereco getEndereco() {
	return this.endereco;
    }

    public void setTelefone(String telefone) throws Exception {
	if (telefone != null && !telefone.isEmpty())
	   this.telefone = telefone;
	else
	    throw new Exception();
    }

    public String getTelefone() {
        return this.telefone;
    }
    
    public Date getDataCadastro() {
	return dataCadastro;
    }
    
    public void setDataVenda(Date dataCadastro) {
	try {
	    if (dataCadastro != null) {
		this.dataCadastro = dataCadastro;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
