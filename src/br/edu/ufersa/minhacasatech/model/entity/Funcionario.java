package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;

public class Funcionario extends Usuario {
    private double salario;

    public Funcionario() {}

    public Funcionario(String login, String senha, double Salario) throws InvalidInsertException {
	super(login, senha);
	setSalario(salario);
    }

    public void setSalario(double salario) {
	try {
	    if (salario > 0) {
		this.salario = salario;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public double getSalario() {
	return this.salario;
    }
}
