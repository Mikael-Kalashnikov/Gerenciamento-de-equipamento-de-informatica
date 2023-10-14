package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import java.util.List;

public interface BaseBO<Entity> {
    
    public void cadastrar(Entity e) throws InvalidInsertException, AlreadyExistsException;
    public void alterar(Entity e) throws InvalidInsertException;
    public void remover(Entity e);
    public Entity buscarPorId(Entity e) throws NotFoundException;
    public List<Entity> listar();
    
}
