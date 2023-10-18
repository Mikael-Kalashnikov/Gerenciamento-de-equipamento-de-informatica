package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.VendaDAO;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import java.util.List;

public class VendaBO implements BaseBO<Venda> {

    VendaDAO vendao;
    
    @Override
    public Venda cadastrar(Venda e) throws InvalidInsertException, AlreadyExistsException {
        vendao = new VendaDAO();
        e.setId(vendao.inserir(e));
        return e;
    }
    
    @Override
    public void alterar(Venda e) throws InvalidInsertException {
        vendao = new VendaDAO();
        vendao.alterar(e);
    }

    @Override
    public void remover(Venda e) {
        vendao = new VendaDAO();
        vendao.deletar(e);
    }

    @Override
    public Venda buscarPorId(Venda e) throws NotFoundException {
        vendao = new VendaDAO();
        return vendao.buscarPorId(e);
    }
    
    @Override
    public List<Venda> listar() {
        vendao = new VendaDAO();
        return vendao.listar();
    }
    
}
