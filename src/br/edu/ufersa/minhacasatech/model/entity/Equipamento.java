package br.edu.ufersa.minhacasatech.model.entity;

import java.sql.Date;

public class Equipamento {
    private Long id;
    private String nome;
    private String numSerie;
    private double preco;
    private int quantidade;
    private Local local;
    private Responsavel responsavel;
    private Date dataCadastro;

    public Equipamento() {}
    
    public Equipamento(String nome, String numSerie, double preco, int quantidade, Local local, Responsavel responsavel) {
	setNome(nome);
	setNumSerie(numSerie);
	setPreco(preco);
	setQuantidade(quantidade);
	setLocal(local);
	setResponsavel(responsavel);
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

    public void setNumSerie(String numSerie) {
        try {
	    if (numSerie != null && !numSerie.isEmpty()) {
		this.numSerie = numSerie;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getNumSerie() {
        return this.numSerie;
    }

    public void setPreco(double preco) {
        try {
	    if (preco >= 0) {
		this.preco = preco;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public double getPreco() {
        return this.preco;
    }

    public void setQuantidade(int quantidade) {
        try {
	    if (quantidade > 0) {
		this.quantidade = quantidade;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setLocal(Local local) {
        try {
	    if (local != null) {
		this.local = local;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Local getLocal() {
        return this.local;
    }

    public void setResponsavel(Responsavel responsavel) {
        try {
	    if (responsavel != null) {
		this.responsavel = responsavel;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Responsavel getResponsavel() {
        return this.responsavel;
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
	return "ID: " + id + " | Nome: " + nome + " | Numero de Serie: " + numSerie + " | preco: R$" + preco + " | quantidade: " + quantidade + " | Local: " + local.toString() + " | Responsavel: " + responsavel.getNome();
    }
}
