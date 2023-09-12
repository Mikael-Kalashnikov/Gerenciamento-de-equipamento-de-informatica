public class Cliente {
	private String nome;
	private String endereco;
	private String cpf;
	
	public Cliente() {}
	public Cliente(String nome, String cpf) throws Exception {
		setNome(nome);
		setCpf(cpf);
	}
	
	public void setNome(String nome) throws Exception {
		if (nome != null && nome != "")
			this.nome = nome;
		else
			throw new Exception();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setEndereco(String endereco) throws Exception {
		if (endereco != null && endereco != "")
			this.endereco = endereco;
		else
			throw new Exception();
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public void setCpf(String cpf) throws Exception {
		if (cpf != null && cpf != "" && cpf.length() == 11)
			this.cpf = endereco;
		else
			throw new Exception();
	}
	
	public String getCpf() {
		return this.cpf;
	}
}