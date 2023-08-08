class Cliente {
	private String nome;
	private String endereco;
	private String cpf;
	
	public Cliente() {}
	public Cliente(String nome, String cpf) {
		setNome(nome);
		setCpf(cpf);
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
	
	public void setEndereco(String endereco) {
		if (endereco != null && endereco != "")
			this.endereco = endereco;
		else
			System.out.println("Digite um endereço válido.");
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setCpf(String cpf) {
		if (cpf != null && cpf != "" && cpf.length() == 11)
			this.cpf = endereco;
		else
			System.out.println("Digite um cpf válido.");
	}
	
	public String getCpf() {
		return this.cpf;
	}
}