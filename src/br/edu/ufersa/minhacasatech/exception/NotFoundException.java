package br.edu.ufersa.minhacasatech.exception;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super("Entidade não encontrada");
    }
}
