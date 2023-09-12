public class Vendas {
    private Cliente cliente;
    private Equipamento[] equipamentos;

    public Vendas(Cliente cliente, Equipamento[] equipamentos) throws Exception {
        setCliente(cliente);
        setEquipamentos(equipamentos);
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
