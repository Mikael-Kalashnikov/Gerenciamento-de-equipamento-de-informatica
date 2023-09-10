public class Local {
    private String nome;
    private String nomeCompartimento;

    public Local() {}
	
    public Local(String nome, String nomeCompartimento) {
        setNome(nome);
        setNomeCompartimento(nomeCompartimento);
    }

    public void setNome(String nome) {
        if (nome != null && !nome.equals("")) {
            this.nome = nome;
        } else {
            System.out.println("Nome do local nao pode ser vazio.");
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNomeCompartimento(String nomeCompartimento) {
        if (nomeCompartimento != null && !nomeCompartimento.equals("")) {
            this.nomeCompartimento = nomeCompartimento;
        } else {
            System.out.println("Nome do compartimento nao pode ser vazio.");
        }
    }

    public String getNomeCompartimento() {
        return this.nomeCompartimento;
    }

}