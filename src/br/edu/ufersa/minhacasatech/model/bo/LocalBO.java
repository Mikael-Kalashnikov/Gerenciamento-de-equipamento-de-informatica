package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.model.dao.LocalDAO;
import java.util.ArrayList;
import java.util.List;

public class LocalBO implements BaseBO<Local> {
    
    private LocalDAO locdao;
    
    @Override
    public Local cadastrar(Local loc) throws InvalidInsertException, AlreadyExistsException {
        locdao = new LocalDAO();
        // verificar se o local ja existe
        if (locdao.existeLocal(loc) != null) {
            throw new AlreadyExistsException("Este local já está cadastrado");
        }
        else {
            // inserir o local no banco
            loc.setId(locdao.inserir(loc));
        }
        return loc;
    }
    
    @Override
    public void alterar(Local loc) throws InvalidInsertException {
        locdao = new LocalDAO();
	locdao.alterar(loc);
    }

    @Override
    public void remover(Local loc) {
        locdao = new LocalDAO();
	locdao.deletar(loc);
    }
    
    @Override
    public Local buscarPorId(Local loc) throws NotFoundException {
        locdao = new LocalDAO();
	loc = locdao.buscarPorId(loc);
	return loc;
    }
    
    public Local buscarPorNomeCompartimento(Local loc) {
        locdao = new LocalDAO();
        return locdao.existeLocal(loc);
    }
    
    public ArrayList<String> listarNomes() {
        locdao = new LocalDAO();
        return locdao.listarLocais();
    }
    
    @Override
    public List<Local> listar() {
        locdao = new LocalDAO();
	List<Local> lista = locdao.listar();
	return lista;
    }
    
}
