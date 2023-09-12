public class Local {
    private String nome;
    private String nomeCompartimento;

    public Local() {}
	
    public Local(String nome, String nomeCompartimento) throws Exception {
        setNome(nome);
        setNomeCompartimento(nomeCompartimento);
    }

    public void setNome(String nome) throws Exception {
        if (nome != null && !nome.equals("")) {
            this.nome = nome;
        } else {
            throw new Exception();
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setNomeCompartimento(String nomeCompartimento) throws Exception {
        if (nomeCompartimento != null && !nomeCompartimento.equals("")) {
            this.nomeCompartimento = nomeCompartimento;
        } else {
            throw new Exception();
        }
    }

    public String getNomeCompartimento() {
        return this.nomeCompartimento;
    }

}