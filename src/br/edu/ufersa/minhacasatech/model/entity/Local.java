package br.edu.ufersa.minhacasatech.model.entity;

import java.sql.Date;

public class Local {
    private Long id;
    private String nome;
    private String nomeCompartimento;
    private Date dataCadastro;

    public Local() {}
	
    public Local(String nome, String nomeCompartimento) {
	setNome(nome);
	setNomeCompartimento(nomeCompartimento);
    }

    public void setNome(String nome) {
        try {
	    if (nome != null && !nome.isEmpty()) {
		this.nome = nome;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getNome() {
        return this.nome;
    }

    public void setNomeCompartimento(String nomeCompartimento) {
        try {
	    if (nomeCompartimento != null && !nomeCompartimento.isEmpty()) {
		this.nomeCompartimento = nomeCompartimento;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getNomeCompartimento() {
        return this.nomeCompartimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        try {
	    if (id > 0) {
		this.id = id;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
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

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Compartimento: " + nomeCompartimento;
    }
}
