package br.edu.ufersa.minhacasatech.model.dao;

import java.util.List;

public interface BaseDAO<Entity> {
    
    public Long inserir(Entity e);
    public void deletar(Entity e);
    public void alterar(Entity e);
    public Entity buscar(Entity e);
    public List<Entity> listar();
    
}
