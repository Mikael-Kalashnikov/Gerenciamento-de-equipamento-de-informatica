package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Equipamento {
    
    private Long id;
    private String nome;
    private String serial;
    private double preco;
    private int estoque;
    private int qtdCompra;
    private double valorUnitario;
    private int vendidos;
    private Local local;
    private Funcionario responsavel;
    private String dataCadastro;
    
    public Equipamento() {
    }
    
    public Equipamento(String nome, String serial, double preco, int estoque, Local local, Funcionario responsavel) throws InvalidInsertException {
	setNome(nome);
	setSerial(serial);
	setPreco(preco);
	setEstoque(estoque);
        setLocal(local);
        setResponsavel(responsavel);
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

    public void setEstoque(int estoque) throws InvalidInsertException {
        if (estoque >= 0)
            this.estoque = estoque;
        else
            throw new InvalidInsertException("Quantidade de equipamentos inválida!");
    }
    
    public int getEstoque() {
        return this.estoque;
    }
    
    public void setQtdCompra(int qtdCompra) throws InvalidInsertException {
        if (qtdCompra > 0) {
            this.qtdCompra = qtdCompra;
        }
        else
            throw new InvalidInsertException("Estoque invalido");
    }
    
    public int getQtdCompra() {
        return this.qtdCompra;
    }
    
    public double getValorUnitario() {
        return valorUnitario;
    }
    
    public void setValorUnitario(double valorUnitario) throws InvalidInsertException  {
        if (valorUnitario > 0.0)
            this.valorUnitario = valorUnitario;
	 else
	    throw new InvalidInsertException("Valor unitário inválido!");
    }
    
    public void setVendidos(int vendidos) throws InvalidInsertException {
        if (vendidos >= 0)
            this.vendidos = vendidos;
        else
            throw new InvalidInsertException("Quantidade de equipamentos vendidos inválida!");
    }
    
    public int getVendidos() {
        return this.vendidos;
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
	return "Serial: " + serial + " | Nome: " + nome + " | preço: R$" + preco + " | estoque: " + estoque + " | Local: " + local.toString() + " | Responsável: " + responsavel.getNome();
    }
}
