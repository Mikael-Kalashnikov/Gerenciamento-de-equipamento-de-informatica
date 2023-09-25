package br.edu.ufersa.MinhaCasaTech.src.model.entity;

public class Local {
    private Long id;
    private String nome;
    private String nomeCompartimento;

    public Local() {}
	
    public Local(String nome, String nomeCompartimento) {
        try {
            setNome(nome);
            setNomeCompartimento(nomeCompartimento);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNome(String nome) throws Exception {
        if (nome != null && !nome.equals("")) {
            this.nome = nome;
        } else {
            throw new Exception();
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNomeCompartimento(String nomeCompartimento) throws Exception {
        if (nomeCompartimento != null && !nomeCompartimento.equals("")) {
            this.nomeCompartimento = nomeCompartimento;
        } else {
            throw new Exception();
        }
    }

    public String getNomeCompartimento() {
        return this.nomeCompartimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws Exception {
        if (id > 0)
            this.id = id;
        else throw new Exception();
    }

    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Compartimento: " + nomeCompartimento;
    }

}