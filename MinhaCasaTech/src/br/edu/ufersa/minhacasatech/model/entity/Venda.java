package br.edu.ufersa.minhacasatech.model.entity;

import java.sql.Date;
import java.util.List;

public class Venda {
    public enum StatusVenda {
	EM_PROCESSAMENTO,
	APROVADA,
	CANCELADA,
	CONCLUIDA;
    }
    
    private Cliente cliente;
    private List<Equipamento> equipamentos;
    private Date dataVenda;
    private double valorTotal;
    private StatusVenda status;
    
    public Venda() {}
    
    public Venda(Cliente cliente, List<Equipamento> equipamentos, Date dataVenda, double valorTotal, StatusVenda status) {
	setCliente(cliente);
	setEquipamentos(equipamentos);
	setDataVenda(dataVenda);
	setValorTotal(valorTotal);
	setStatus(status);
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente)  {
        try {
	    if (cliente != null) {
		this.cliente = cliente;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        try {
	    if (equipamentos != null) {
		this.equipamentos = equipamentos;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public Date getDataVenda() {
	return dataVenda;
    }
    
    public void setDataVenda(Date dataVenda) {
	try {
	    if (dataVenda != null) {
		this.dataVenda = dataVenda;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public double getValorTotal() {
	return valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
	try {
	    if (valorTotal > 0) {
		this.valorTotal = valorTotal;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public StatusVenda getStatus() {
	return status;
    }
    
    public void setStatus(StatusVenda status) {
	try {
	    if (status != null) {
		this.status = status;
	    }
	    else {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
