class Funcionario {
	private String nome;
	private String login;
	private String senha;
	private String endereco;
	private String telefone;
	private Double salario;
	
	public Funcionario() {}
	
	public Funcionario(String login, String senha) {
		setLogin(login);
		setSenha(senha);
	}
	
	public void setNome(String nome) {
		if (nome != null && nome != "")
			this.nome = nome;
		else
			System.out.println("Digite um nome válido.");
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setLogin(String login) {
		if (login != null && login != "")
			this.login = login;
		else
			System.out.println("Digite um login válido.");
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setSenha(String senha) {
		if (senha != null && senha != "")
			this.senha = senha;
		else
			System.out.println("Digite uma senha válida.");
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setEndereco(String endereco) {
		if (endereco != null && endereco != "")
			this.endereco = endereco;
		else
			System.out.println("Digite um endereço válido.");
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setTelefone(String telefone) {
		if (telefone != null && telefone != "")
			this.telefone = telefone;
		else
			System.out.println("Digite um telefone válido.");
	}
	
	public String getTelefone() {
		return this.telefone;
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
	
	public boolean verificaLogin(String login, String senha) {
		if (this.login == login && this.senha == senha) {
			System.out.println("Login realizado com sucesso.");
			return true;
		}
		else
		{
			System.out.println("Login ou senha incorretos.");
			return false;
		}
	}
	
}