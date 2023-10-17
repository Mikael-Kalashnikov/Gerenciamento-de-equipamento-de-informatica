package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Equipamento {
    private Long id;
    private String nome;
    private String serial;
    private double preco;
    private int quantidade;
    private int vendidos;
    private Local local;
    private Funcionario responsavel;
    private String dataCadastro;
    
    public Equipamento() {
    }
    
    public Equipamento(String nome, String serial, double preco, int quantidade) throws InvalidInsertException {
	setNome(nome);
	setSerial(serial);
	setPreco(preco);
	setQuantidade(quantidade);
    }
    
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) throws InvalidInsertException {
        if (id > 0)
            this.id = id;
	else
	    throw new InvalidInsertException("ID inválido!");
    }

    public void setNome(String nome) throws InvalidInsertException {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
        else
	    throw new InvalidInsertException("Nome inválido!");
    }

    public String getNome() {
        return this.nome;
    }

    public boolean validarSerial(String s) {
        String regex = "^[A-Z0-9]{10}-[A-Z0-9]{4}$";
        return s.matches(regex);
    }
    
    public void setSerial(String serial) throws InvalidInsertException {
        if (serial != null && !serial.isEmpty() && validarSerial(serial))
            this.serial = serial;
        else
	    throw new InvalidInsertException("Código serial inválido!");
    }
    
    public String getSerial() {
        return this.serial;
    }

    public void setPreco(double preco) throws InvalidInsertException {
        if (preco > 0.0)
            this.preco = preco;
        else
            throw new InvalidInsertException("Preço inválido!");
    }

    public double getPreco() {
        return this.preco;
    }

    public void setQuantidade(int quantidade) throws InvalidInsertException {
        if (quantidade > 0.0)
            this.quantidade = quantidade;
        else
            throw new InvalidInsertException("Quantidade inválida!");
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setLocal(Local local) throws InvalidInsertException {
        if (local != null)
            this.local = local;
        else
            throw new InvalidInsertException("Local inválido!");
    }

    public Local getLocal() {
        return this.local;
    }

    public void setResponsavel(Funcionario responsavel) throws InvalidInsertException {
        if (responsavel != null)
            this.responsavel = responsavel;
        else
            throw new InvalidInsertException("Responsável inválido!");
    }
    
    public Funcionario getResponsavel() {
        return this.responsavel;
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
	return "ID: " + id + " | Nome: " + nome + " | Numero de Serie: " + serial + " | preco: R$" + preco + " | quantidade: " + quantidade + " | Local: " + local.toString() + " | Responsavel: " + responsavel.getNome();
    }
}
