package br.edu.ufersa.MinhaCasaTech.src.model.entity;

public abstract class Usuario {
	private String nome;
	private String login;
	private String senha;
	private String endereco;
	private String telefone;
	
	public Usuario() {}
	
	public Usuario(String login, String senha) {
		try {
            setLogin(login);
            setSenha(senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
	
	public void setLogin(String login) throws Exception {
		if (login != null && login != "")
			this.login = login;
		else
			throw new Exception();
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setSenha(String senha) throws Exception {
		if (senha != null && senha != "")
			this.senha = senha;
		else
			throw new Exception();
	}
	
	public String getSenha() {
		return this.senha;
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
	
	public void setTelefone(String telefone) throws Exception {
		if (telefone != null && telefone != "")
			this.telefone = telefone;
		else
			throw new Exception();
	}
	
	public String getTelefone() {
		return this.telefone;
	}	
}
