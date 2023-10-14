package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.util.List;

public class FuncionarioBO implements BaseBO<Funcionario> {

    @Override
    public void cadastrar(Funcionario func) throws InvalidInsertException {
        
    }

    @Override
    public void alterar(Funcionario func) throws InvalidInsertException {
        
    }

    @Override
    public void remover(Funcionario func) {
        
    }

    @Override
    public Funcionario buscarPorId(Funcionario func) throws NotFoundException {
        return null;
    }

    @Override
    public List<Funcionario> listar() {
        return null;
    }
    
}
