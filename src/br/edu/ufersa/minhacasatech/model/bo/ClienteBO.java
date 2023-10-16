package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.ClienteDAO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import java.util.List;

public class ClienteBO implements BaseBO<Cliente> {
    
    private ClienteDAO clidao;

    @Override
    public Cliente cadastrar(Cliente cli) throws InvalidInsertException, AlreadyExistsException {
	clidao = new ClienteDAO();
	if (clidao.buscarPorCpf(cli) == null) {
            cli.setId(clidao.inserir(cli));
        } else {
            throw new AlreadyExistsException("O cpf deste cliente já está cadastrado no sistema!");
        }
	return cli;
    }
    
    @Override
    public void alterar(Cliente e) throws InvalidInsertException {
	clidao = new ClienteDAO();
        clidao.alterar(e);
    }

    @Override
    public void remover(Cliente e) {
	clidao = new ClienteDAO();
        clidao.deletar(e);
    }

    @Override
    public Cliente buscarPorId(Cliente e) throws NotFoundException {
	clidao = new ClienteDAO();
        return clidao.buscarPorId(e);
    }
    
    public Cliente buscarPorCpf(Cliente e) {
        clidao = new ClienteDAO();
        return clidao.buscarPorCpf(e);
    }
    
    @Override
    public List<Cliente> listar() {
        clidao = new ClienteDAO();
	return clidao.listar();
    }
    
}
