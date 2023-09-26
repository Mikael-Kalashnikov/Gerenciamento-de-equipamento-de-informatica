package br.edu.ufersa.minhacasatech.exception;

public class InvalidInsertException extends Exception {
    
    public InvalidInsertException(String s) {
	super ("Não foi possível inserir " + s);
    }
}
