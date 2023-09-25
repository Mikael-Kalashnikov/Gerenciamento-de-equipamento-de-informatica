package br.edu.ufersa.MinhaCasaTech.src.model.entity;

public class Venda {
    private Cliente cliente;
    private Equipamento[] equipamentos;

    public Venda() {}
    public Venda(Cliente cliente, Equipamento[] equipamentos) {
        try {
            setCliente(cliente);
            setEquipamentos(equipamentos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) throws Exception {
        if (cliente != null)
            this.cliente = cliente;
        else
            throw new Exception();
    }

    public Equipamento[] getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Equipamento[] equipamentos) throws Exception {
        if (equipamentos != null)
            this.equipamentos = equipamentos;
        else
            throw new Exception();
    }
}
