package br.edu.ufersa.minhacasatech.model.entity;

public class Venda {
    private Cliente cliente;
    private Equipamento[] equipamentos;

    public Venda() {}
    
    public Venda(Cliente cliente, Equipamento[] equipamentos) {
	setCliente(cliente);
	setEquipamentos(equipamentos);
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

    public Equipamento[] getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Equipamento[] equipamentos) {
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
}
