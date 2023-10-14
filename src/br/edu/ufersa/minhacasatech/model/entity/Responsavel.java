package br.edu.ufersa.minhacasatech.model.entity;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;

public class Responsavel extends Usuario {
	
    public Responsavel() {}

    public Responsavel(String login, String senha) throws InvalidInsertException {
	super(login, senha);
    }
}
