package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Funcionario {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String telefone;
    private String cpf;
    private String endereco;
    private double totalVendas;
    private boolean isResponsavel;
    private String dataCadastro;
    
    public Funcionario() {}
    
    public Funcionario(String nome, String login, String senha, String telefone, String cpf, String endereco) throws InvalidInsertException {
        setNome(nome);
	setLogin(login);
	setSenha(senha);
        setTelefone(telefone);
        setCpf(cpf);
        setEndereco(endereco);
    }
    
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) throws InvalidInsertException {
	if (id > 0)
	    this.id = id;
	else
	    throw new InvalidInsertException("ID de Usuário inválido");
    }

    public void setNome(String nome) throws InvalidInsertException {
	if (nome != null && !nome.isEmpty())
	    this.nome = nome;
	else
	    throw new InvalidInsertException("Nome inválido");
    }

    public String getNome() {
	return this.nome;
    }

    public void setLogin(String login) throws InvalidInsertException {
	if (login != null && !login.isEmpty())
            this.login = login;
        else
            throw new InvalidInsertException("Login inválido");
    }

    public String getLogin() {
	return this.login;
    }

    public void setSenha(String senha) throws InvalidInsertException {
        if (senha != null && !senha.isEmpty())
            this.senha = senha;
        else
            throw new InvalidInsertException("Senha inválida");
    }

    public String getSenha() {
	return this.senha;
    }

    public boolean enderecoValido(String endereco) {
        String regex = "^[A-Z][A-Za-z\\s]+,\\s\\d+$";
        return endereco.matches(regex);
    }
    
    public void setEndereco(String endereco) throws InvalidInsertException {
        if (endereco != null && !endereco.isEmpty() && enderecoValido(endereco)) {
            this.endereco = endereco;
        }
        else {
            throw new InvalidInsertException("Endereço inválido!");
        }
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public boolean telefoneValido(String telefone) {
        String regex = "\\(\\d{2}\\)\\d{5}-\\d{4}";
        return telefone.matches(regex);
    }

    public void setTelefone(String telefone) throws InvalidInsertException {
	if (telefone != null && !telefone.isEmpty() && telefoneValido(telefone))
	   this.telefone = telefone;
	else
	    throw new InvalidInsertException("Telefone inválido!");
    }

    public String getTelefone() {
        return this.telefone;
    }
    
    public boolean cpfValido(String cpf) {
        String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return cpf.matches(regex);
    }
    
    public void setCpf(String cpf) throws InvalidInsertException {
	if (cpf != null && !cpf.isEmpty() && cpfValido(cpf))
	   this.cpf = cpf;
	else
	    throw new InvalidInsertException("CPF inválido!");
    }

    public String getCpf() {
        return this.cpf;
    }
    
    public double getTotalVendas() {
        return this.totalVendas;
    }
    
    public void setTotalVendas(double totalVendas) throws InvalidInsertException {
        if (totalVendas >= 0.0)
            this.totalVendas = totalVendas;
        else
            throw new InvalidInsertException("Valor total de vendas inválido!");
    }
    
    public void setIsResponsavel(boolean responsavel) {
        this.isResponsavel = responsavel;
    }
    
    public boolean getIsResponsavel() {
        return this.isResponsavel;
    }
    
    public String getDataCadastro() {
	return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) throws InvalidInsertException {
        if (dataCadastro != null) {
            String data = dataCadastro.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.dataCadastro = data;
        }
        else
	    throw new InvalidInsertException("Data de cadastro inválida");
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
}
