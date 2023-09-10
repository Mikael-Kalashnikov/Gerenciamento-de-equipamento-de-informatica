public class Equipamento {
    private String nome;
    private String numSerie;
    private Double preco;
    private int quantidade;
    private Local local;
    private Responsavel responsavel;

    public Equipamento() {}

    public Equipamento(String nome, String numSerie, Double preco, int quantidade, Local local, Responsavel responsavel) {
        setNome(nome);
        setNumSerie(numSerie);
        setPreco(preco);
        setQuantidade(quantidade);
        setLocal(local);
        setResponsavel(responsavel);
    }

    public void setNome(String nome) {
        if (nome != null && !nome.equals("")) {
            this.nome = nome;
        } else {
            System.out.println("O nome do equipamento nao pode ser vazio.");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNumSerie(String numSerie) {
        if (numSerie != null && !numSerie.equals("")) {
            this.numSerie = numSerie;
        } else {
            System.out.println("O número de série do equipamento nao pode ser vazio.");
        }
    }

    public String getNumSerie() {
        return this.numSerie;
    }

    public void setPreco(Double preco) {
        if (preco != null && preco >= 0) {
            this.preco = preco;
        } else {
            System.out.println("O preço do equipamento nao pode ser negativo.");
        }
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        } else {
            System.out.println("A quantidade do equipamento nao pode ser negativa.");
        }
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getLocal() {
        return this.local;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Responsavel getResponsavel() {
        return this.responsavel;
    }
}
