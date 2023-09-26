package br.edu.ufersa.minhacasatech.model.entity;

public class Endereco {
    private Long id;
    private String rua;
    private int numero;

    public Endereco(){}

    public Endereco(String rua, int numero) {
	setRua(rua);
	setNumero(numero);
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        try {
	    if (rua != null && !rua.isEmpty()) {
		this.rua = rua;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        try {
	    if (numero > 0) {
		this.numero = numero;
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
        return rua + ", " + numero;
    }
}
