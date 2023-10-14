package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Local {
    private Long id;
    private String nome;
    private String nomeCompartimento;
    private String dataCadastro;
    
    public Local() {}
    
    public Local(String nome, String nomeCompartimento) throws InvalidInsertException {
	setNome(nome);
	setNomeCompartimento(nomeCompartimento);
    }

    public void setNome(String nome) throws InvalidInsertException {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
        else
	    throw new InvalidInsertException("Local inválido");
    }

    public String getNome() {
        return this.nome;
    }

    public void setNomeCompartimento(String nomeCompartimento) throws InvalidInsertException {
        if (nomeCompartimento != null && !nomeCompartimento.isEmpty())
            this.nomeCompartimento = nomeCompartimento;
        else
	    throw new InvalidInsertException("Compartimento inválido");
    }

    public String getNomeCompartimento() {
        return this.nomeCompartimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws InvalidInsertException {
        if (id > 0)
            this.id = id;
        else
	    throw new InvalidInsertException("ID inválido");
    }
    
    public String getDataCadastro() {
	return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) throws InvalidInsertException {
        if (dataCadastro != null) {
            String data = dataCadastro.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.dataCadastro = data;
        }
        else
	    throw new InvalidInsertException("Data de cadastro inválida");
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Compartimento: " + nomeCompartimento + " | Data de Cadastro: " + dataCadastro;
    }
}
