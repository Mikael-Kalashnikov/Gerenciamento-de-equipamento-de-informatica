package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;

public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private Date dataCadastro;
    
    public Cliente() {}
    
    public Cliente(String nome, String cpf, String telefone, Endereco endereco) throws InvalidInsertException {
	setNome(nome);
	setCpf(cpf);
        setTelefone(telefone);
	setEndereco(endereco);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws InvalidInsertException {
        if (id > 0) {
            this.id = id;
        }
        else {
            throw new InvalidInsertException("ID inválido!");
        }
    }
	
    public void setNome(String nome) throws InvalidInsertException {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
        else {
            throw new InvalidInsertException("Nome inválido!");
        }
    }
    
    public String getNome() {
	return this.nome;
    }

    public void setEndereco(Endereco endereco) throws InvalidInsertException {
        if (endereco != null) {
            this.endereco = endereco;
        }
        else {
            throw new InvalidInsertException("Endereço inválido!");
        }
    }

    public Endereco getEndereco() {
	return this.endereco;
    }

    public boolean cpfValido(String cpf) {
        String regex = "\\{d3}\\.\\{d3}\\.\\{d3}\\-\\{d2}";
        return cpf.matches(regex);
    }
    
    public void setCpf(String cpf) throws InvalidInsertException {
	if (cpf != null && !cpf.isEmpty() && cpfValido(cpf))
	   this.cpf = cpf;
	else
	    throw new InvalidInsertException("CPF inválido!");
    }

    public String getCpf() {
        return this.cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) throws InvalidInsertException {
        if (telefone != null && !telefone.isEmpty() && telefone.length() == 14) {
            this.telefone = telefone;
        }
        else {
            throw new InvalidInsertException("Telefone inválido!");
        }
    }
    
    public Date getDataCadastro() {
	return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) throws InvalidInsertException {
        if (dataCadastro != null) {
            this.dataCadastro = dataCadastro;
        }
        else {
            throw new InvalidInsertException("Data de cadastro inválida!");
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Endereco: " + endereco.toString() + " | CPF: " + cpf + " | Telefone: " + telefone;
    }
}
