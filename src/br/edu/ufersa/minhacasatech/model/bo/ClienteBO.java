package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.ClienteDAO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import java.util.List;

public class ClienteBO implements BaseBO<Cliente> {
    
    private ClienteDAO clidao;

    @Override
    public void cadastrar(Cliente cli) throws InvalidInsertException {
	clidao = new ClienteDAO();
	ClienteDAO.getConnection();
	
	clidao.inserir(cli);
	
	ClienteDAO.closeConnection();
    }
    
    @Override
    public void alterar(Cliente e) throws InvalidInsertException {
	
    }

    @Override
    public void remover(Cliente e) {
	
    }

    @Override
    public Cliente buscarPorId(Cliente e) throws NotFoundException {
	return null;
    }

    @Override
    public List<Cliente> listar() {
	return null;
    }
    
}
