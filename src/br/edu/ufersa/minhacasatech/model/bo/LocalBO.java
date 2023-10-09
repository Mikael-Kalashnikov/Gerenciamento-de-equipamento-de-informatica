package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.model.dao.LocalDAO;
import java.util.List;

public class LocalBO implements BaseBO<Local> {

    private LocalDAO locdao;
    
    public LocalBO(LocalDAO locdao) {
	this.locdao = locdao;
    }
    
    @Override
    public void cadastrar(Local loc) throws InvalidInsertException {
	// validar os dados
	
	// verificar se o local ja existe
	
	// inserir o local no banco
	LocalDAO.getConnection();
	locdao.inserir(loc);
	LocalDAO.closeConnection();
    }

    @Override
    public void buscarPorId(Local loc) throws NotFoundException {
	
    }

    @Override
    public List<Local> listar() throws InvalidInsertException {
	return null;
    }

    @Override
    public void alterar(Local loc) throws InvalidInsertException {
	
    }

    @Override
    public void remover(Local loc) throws InvalidInsertException {
	
    }
    
}
