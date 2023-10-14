package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.EnderecoDAO;
import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import java.util.List;

public class EnderecoBO implements BaseBO<Endereco> {

    private EnderecoDAO endao;
    
    @Override
    public void cadastrar(Endereco e) throws InvalidInsertException, AlreadyExistsException {
        endao = new EnderecoDAO();
        // verificar se o endereco ja existe
        if (endao.buscarPorRua(e) != null) {
            throw new AlreadyExistsException("Este endereço já está cadastrado");
        }
        else {
            // inserir o endereco no banco
            e.setId(endao.inserir(e));
        }
    }
    
    @Override
    public void alterar(Endereco e) throws InvalidInsertException {
        endao = new EnderecoDAO();
        endao.alterar(e);
    }
    
    @Override
    public void remover(Endereco e) {
        endao = new EnderecoDAO();
    }
    
    @Override
    public Endereco buscarPorId(Endereco e) throws NotFoundException {
        endao = new EnderecoDAO();
        return null;
    }
    
    @Override
    public List<Endereco> listar() {
        endao = new EnderecoDAO();
        return null;
    }
    
}
