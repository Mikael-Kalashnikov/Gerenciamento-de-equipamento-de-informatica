public class Responsavel {
	private String nome;
	private String login;
	private String senha;
	private String endereco;
	private String telefone;
	
	public Responsavel() {}
	
	public Responsavel(String login, String senha) {
		setLogin(login);
		setSenha(senha);
	}
	
	public void setNome(String nome) {
		if (nome != null && !nome.equals("")) {
			this.nome = nome;
		} else {
			System.out.println("Nome do responsavel nao pode ser vazio.");
		}
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setLogin(String login) {
		if (login != null && !login.equals("")) {
			this.login = login;
		} else {
			System.out.println("Login nao pode ser vazio.");
		}
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setSenha(String senha) {
		if (senha != null && !senha.equals("")) {
			this.senha = senha;
		} else {
			System.out.println("A Senha nao pode ser vazia.");
		}
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setEndereco(String endereco) {
		if (endereco != null && !endereco.equals("")) {
			this.endereco = endereco;
		} else {
			System.out.println("O Endereco nao pode ser vazio.");
		}
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setTelefone(String telefone) {
		if (telefone != null && !telefone.equals("")) {
			this.telefone = telefone;
		} else {
			System.out.println("O telefone nao pode ser vazio.");
		}
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public boolean verificaLogin(String login, String senha) {
		boolean logar;
		if (login.equals(this.login) && senha.equals(this.senha)) {
			System.out.println("Login realizado com sucesso.");
			logar = true;
		} else {
			System.out.println("Login/Senha incorretos.");
			logar = false;
		}
		
		return logar;
	}
	
}