package br.edu.ufersa.MinhaCasaTech.src.model.entity;

public class Funcionario extends Usuario {
	private double salario;
	
	public Funcionario() {}
	
	public Funcionario(String login, String senha, double Salario) {
		super(login, senha);
		try {
            setSalario(salario);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void setSalario(double salario) throws Exception {
		if (salario > 0)
			this.salario = salario;
		else
			throw new Exception();
	}
	
	public double getSalario() {
		return this.salario;
	}
	
}