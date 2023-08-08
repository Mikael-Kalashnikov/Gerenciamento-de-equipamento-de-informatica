public class Venda {
    private Cliente cliente;
    private Equipamento[] equipamentos;

    public Venda(Cliente cliente, Equipamento[] equipamentos) {
        this.cliente = cliente;
        this.equipamentos = equipamentos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento[] getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(Equipamento[] equipamentos) {
        this.equipamentos = equipamentos;
    }
}
