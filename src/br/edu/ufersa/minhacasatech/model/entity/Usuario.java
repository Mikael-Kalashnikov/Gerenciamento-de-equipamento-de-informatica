package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Usuario {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Endereco endereco;
    private String telefone;
    private String tipo;
    private Date dataCadastro;
    
    public Usuario() {}
    
    public Usuario(String login, String senha) throws InvalidInsertException {
	setLogin(login);
	setSenha(senha);
    }
    
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) throws InvalidInsertException {
	if (id > 0)
	    this.id = id;
	else
	    throw new InvalidInsertException("ID de Usuário inválido");
    }

    public void setNome(String nome) throws InvalidInsertException {
	if (nome != null && !nome.isEmpty())
	    this.nome = nome;
	else
	    throw new InvalidInsertException("Nome inválido");
    }

    public String getNome() {
	return this.nome;
    }

    public void setLogin(String login) throws InvalidInsertException {
	if (login != null && !login.isEmpty())
            this.login = login;
        else
            throw new InvalidInsertException("Login inválido");
    }

    public String getLogin() {
	return this.login;
    }

    public void setSenha(String senha) throws InvalidInsertException {
        if (senha != null && !senha.isEmpty())
            this.senha = senha;
        else
            throw new InvalidInsertException("Senha inválida");
    }

    public String getSenha() {
	return this.senha;
    }

    public void setEndereco(Endereco endereco) throws InvalidInsertException {
	if (endereco != null)
	    this.endereco = endereco;
	else
	    throw new InvalidInsertException("Enderço inválido");
    }

    public Endereco getEndereco() {
	return this.endereco;
    }

    public void setTelefone(String telefone) throws InvalidInsertException {
	if (telefone != null && !telefone.isEmpty())
	   this.telefone = telefone;
	else
	    throw new InvalidInsertException("Telefone inválido");
    }

    public String getTelefone() {
        return this.telefone;
    }
    
    public void setTipo(String tipo) throws InvalidInsertException {
	if (tipo != null && !tipo.isEmpty())
	   this.tipo = tipo;
	else
	    throw new InvalidInsertException("Tipo de Usuário inválido");
    }

    public String getTipo() {
        return this.tipo;
    }
    
    public Date getDataCadastro() {
	return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) throws InvalidInsertException {
        if (dataCadastro != null)
            this.dataCadastro = dataCadastro;
        else
            throw new InvalidInsertException("Data de cadastro inválida");
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Login: " + login + " | Senha: " + senha + " | Endereco: " + endereco + " | Telefone: " + telefone + " | Tipo: " + tipo + " | DataCadastro: " + dataCadastro.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
}
