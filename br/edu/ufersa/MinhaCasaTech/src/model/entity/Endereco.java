package br.edu.ufersa.MinhaCasaTech.src.model.entity;

public class Endereco {
    private Long id;
    private String rua;
    private int numero;

    public Endereco(){}

    public Endereco(String rua, int numero) {
        try {
            setRua(rua);
            setNumero(numero);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws Exception {
        if (id > 0)
            this.id = id;
        else throw new Exception();
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) throws Exception {
        if (rua != null && !rua.isEmpty())
            this.rua = rua;
        else throw new Exception();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws Exception {
        if (numero > 0)
            this.numero = numero;
        else throw new Exception();
    }

    public String toString() {
        return rua + ", " + numero;
    }
}
