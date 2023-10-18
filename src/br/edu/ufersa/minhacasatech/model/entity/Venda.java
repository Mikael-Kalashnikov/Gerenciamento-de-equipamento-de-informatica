package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Venda {
    
    private Long id;                        // venda
    private Funcionario funcionario;        // venda
    private Cliente cliente;                // venda
    private List<Equipamento> equipamentos; // venda_equipamento
    private double valorTotal;              // venda
    private String status;                  // venda
    private String dataVenda;               // venda    
    
    public Venda() {}
    
    public Venda(Cliente cliente, Funcionario funcionario) throws InvalidInsertException {
	setCliente(cliente);
        setFuncionario(funcionario);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) throws InvalidInsertException  {
        if (id != null)
            this.id = id;
	 else
	    throw new InvalidInsertException("ID inválido!");
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) throws InvalidInsertException  {
        if (cliente != null)
            this.cliente = cliente;
	 else
	    throw new InvalidInsertException("Cliente inválido!");
    }
    
    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }
    
    public void setEquipamentos(List<Equipamento> equipamentos) throws InvalidInsertException {
        if (equipamentos != null)
            this.equipamentos = equipamentos;
        else
	    throw new InvalidInsertException("Equipamento inválido!");
    }
    
    public double getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) throws InvalidInsertException  {
        if (valorTotal >= 0.0)
            this.valorTotal = valorTotal;
	 else
	    throw new InvalidInsertException("Valor total inválido!");
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) throws InvalidInsertException {
        if (funcionario != null)
            this.funcionario = funcionario;
        else
	    throw new InvalidInsertException("Funcionario inválido!");
    }
    
    public String getDataVenda() {
	return dataVenda;
    }
    
    public void setDataVenda(Date dataVenda) throws InvalidInsertException {
        if (dataVenda != null) {
            String data = dataVenda.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            this.dataVenda = data;
        }
        else
	    throw new InvalidInsertException("Data de venda inválida!");
    }
    
    public String getStatus() {
	return status;
    }
    
    public void setStatus(String status) throws InvalidInsertException {
        if (status != null && !status.isEmpty() && (status.equals("Em Andamento") || status.equals("Aprovada") || status.equals("Cancelada")))
            this.status = status;
        else
            throw new InvalidInsertException("Status de venda inválido!");
    }
    
}
