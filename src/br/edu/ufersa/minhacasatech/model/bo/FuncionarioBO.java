package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AutenticationException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.FuncionarioDAO;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.util.List;

public class FuncionarioBO implements BaseBO<Funcionario> {
    
    private FuncionarioDAO funcdao;
    
    public Funcionario autenticar(Funcionario func) throws AutenticationException {
        funcdao = new FuncionarioDAO();
        Funcionario funcionario = funcdao.buscarPorLogin(func);
        if (funcionario != null && func.getSenha().equals(funcionario.getSenha())) {
            return funcionario;
        }
        throw new AutenticationException("Usuário e/ou senha incorretos");
    }
    
    @Override
    public void cadastrar(Funcionario func) throws InvalidInsertException {
        funcdao = new FuncionarioDAO();
        func.setId(funcdao.inserir(func));
    }
    
    @Override
    public void alterar(Funcionario func) throws InvalidInsertException {
        funcdao = new FuncionarioDAO();
    }

    @Override
    public void remover(Funcionario func) {
        funcdao = new FuncionarioDAO();
    }

    @Override
    public Funcionario buscarPorId(Funcionario func) throws NotFoundException {
        funcdao = new FuncionarioDAO();
        return null;
    }
    
    public Funcionario buscarPorLogin(Funcionario func) throws NotFoundException, AutenticationException {
        funcdao = new FuncionarioDAO();
        func = funcdao.buscarPorLogin(func);
        if (func != null) {
            return func;
        }
        throw new NotFoundException("Funcionario nao encontrado");
    }
    
    @Override
    public List<Funcionario> listar() {
        funcdao = new FuncionarioDAO();
        return null;
    }
    
}
