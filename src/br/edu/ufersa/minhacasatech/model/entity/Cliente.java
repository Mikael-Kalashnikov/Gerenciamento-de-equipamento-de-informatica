package br.edu.ufersa.MinhaCasaTech.src.model.entity;

public class Cliente {
    private Long id;
	private String nome;
    private String cpf;
	private Endereco endereco;
	
	public Cliente() {}
	public Cliente(String nome, String cpf, Endereco endereco) {
        try {
            setNome(nome);
		    setCpf(cpf);
            setEndereco(endereco);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws Exception {
        if (id > 0)
            this.id = id;
        else throw new Exception();
    }
	
	public void setNome(String nome) throws Exception {
		if (nome != null && nome != "")
			this.nome = nome;
		else
			throw new Exception();
	}
	
	public String getNome() {
		return this.nome;
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
	
	public void setCpf(String cpf) throws Exception {
		if (cpf != null && !cpf.isEmpty() && cpf.length() <= 14)
			this.cpf = cpf;
		else
			throw new Exception();
	}
	
	public String getCpf() {
		return this.cpf;
	}

    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Endereco: " + endereco.getId() + " | CPF: " + cpf;
    }
}
