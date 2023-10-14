package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;

public class Endereco {
    private Long id;
    private String rua;
    private int numero;
    
    public Endereco() {}

    public Endereco(Long id) throws InvalidInsertException {
        setId(id);
    }
    
    public Endereco(String rua, int numero) throws InvalidInsertException {
	setRua(rua);
	setNumero(numero);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws InvalidInsertException {
        if (id > 0)
	    this.id = id;
	else
	    throw new InvalidInsertException("ID de Endereco invalido");
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) throws InvalidInsertException {
        if (rua != null && !rua.isEmpty())
            this.rua = rua;
	else
	    throw new InvalidInsertException("Rua invalida");
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws InvalidInsertException {
        if (numero > 0)
            this.numero = numero;
        else
	    throw new InvalidInsertException("Numero invalido");
    }
    
    @Override
    public String toString() {
        return rua + ", " + numero;
    }
}
