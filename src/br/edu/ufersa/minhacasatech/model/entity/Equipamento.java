package br.edu.ufersa.minhacasatech.model.entity;

public class Equipamento {
    private String nome;
    private String numSerie;
    private double preco;
    private int quantidade;
    private Local local;
    private Responsavel responsavel;

    public Equipamento() {}
    public Equipamento(String nome, String numSerie, double preco, int quantidade, Local local, Responsavel responsavel) {
        try {
            setNome(nome);
            setNumSerie(numSerie);
            setPreco(preco);
            setQuantidade(quantidade);
            setLocal(local);
            setResponsavel(responsavel);
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

    public void setNumSerie(String numSerie) throws Exception {
        if (numSerie != null && !numSerie.isEmpty()) {
            this.numSerie = numSerie;
        } else {
            throw new Exception();
        }
    }

    public String getNumSerie() {
        return this.numSerie;
    }

    public void setPreco(double preco) throws Exception {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            throw new Exception();
        }
    }

    public double getPreco() {
        return this.preco;
    }

    public void setQuantidade(int quantidade) throws Exception {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        } else {
            throw new Exception();
        }
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setLocal(Local local) throws Exception {
        if (local != null)
            this.local = local;
        else
            throw new Exception();
    }

    public Local getLocal() {
        return this.local;
    }

    public void setResponsavel(Responsavel responsavel) throws Exception {
        if (responsavel != null)
            this.responsavel = responsavel;
        else
            throw new Exception();
    }

    public Responsavel getResponsavel() {
        return this.responsavel;
    }
}
