package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Venda {
    
    private Long id;
    private Cliente cliente;
    private Equipamento equipamento;
    private Funcionario funcionario;
    private String dataVenda;
    private String status;
    
    public Venda() {}
    
    public Venda(Cliente cliente, Equipamento equipamento, Funcionario funcionario) throws InvalidInsertException {
	setCliente(cliente);
	setEquipamento(equipamento);
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

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) throws InvalidInsertException {
        if (equipamento != null)
            this.equipamento = equipamento;
        else
	    throw new InvalidInsertException("Equipamento inválido!");
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
        if (status != null && !status.isEmpty())
            this.status = status;
        else
            throw new InvalidInsertException("Status de venda inválido!");
    }
    
}
