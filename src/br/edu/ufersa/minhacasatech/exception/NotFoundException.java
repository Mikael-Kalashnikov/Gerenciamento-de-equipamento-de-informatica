package br.edu.ufersa.minhacasatech.exception;

public class NotFoundException extends Exception{

    public NotFoundException() {
	super ("Entidade não encontrada");
    }
}
