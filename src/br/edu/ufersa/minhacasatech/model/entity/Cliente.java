package br.edu.ufersa.minhacasatech.model.entity;

public class Cliente {
    private Long id;
    private String nome;
    private String cpf;
    private Endereco endereco;
    
    public Cliente() {}
    
    public Cliente(String nome, String cpf, Endereco endereco) {
	setNome(nome);
	setCpf(cpf);
	setEndereco(endereco);
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

    public void setEndereco(Endereco endereco) {
	try {
	    if (endereco != null) {
		this.endereco = endereco;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Endereco getEndereco() {
	return this.endereco;
    }

    public void setCpf(String cpf) {
	try {
	    if (cpf != null && !cpf.isEmpty() && cpf.length() == 14) {
		this.cpf = cpf;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String getCpf() {
	return this.cpf;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Endereco: " + endereco.toString() + " | CPF: " + cpf;
    }
}
