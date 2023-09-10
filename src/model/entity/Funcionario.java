public class Funcionario extends Usuario {
	private Double salario;
	
	public Funcionario() {}
	
	public Funcionario(String login, String senha) {
		super(login, senha);
	}
	
	public void setSalario(Double salario) {
		if (salario > 0)
			this.salario = salario;
		else
			System.out.println("O valor de um salário não pode ser nulo ou negativo.");
	}
	
	public Double getSalario() {
		return this.salario;
	}
	
}