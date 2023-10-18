package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Local {
    private Long id;
    private String nome;
    private String compartimento;
    private String dataCadastro;
    
    public Local() {}
    
    public Local(String nome, String compartimento) throws InvalidInsertException {
	setNome(nome);
	setCompartimento(compartimento);
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

    public void setCompartimento(String compartimento) throws InvalidInsertException {
        if (compartimento != null && !compartimento.isEmpty())
            this.compartimento = compartimento;
        else
	    throw new InvalidInsertException("Compartimento inválido");
    }

    public String getCompartimento() {
        return this.compartimento;
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
        return nome + ", " + compartimento;
    }
}
