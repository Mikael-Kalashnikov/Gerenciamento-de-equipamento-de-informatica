package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.AutenticationException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.FuncionarioDAO;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.util.ArrayList;
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
    public Funcionario cadastrar(Funcionario func) throws InvalidInsertException, AlreadyExistsException {
        funcdao = new FuncionarioDAO();
        if (funcdao.buscarPorLogin(func) != null) {
            throw new AlreadyExistsException("Login e/ou senha inválidos!");
        }
        func.setId(funcdao.inserir(func));
        return func;
    }
    
    @Override
    public void alterar(Funcionario func) throws InvalidInsertException {
        funcdao = new FuncionarioDAO();
        funcdao.alterar(func);
    }
    
    @Override
    public void remover(Funcionario func) {
        funcdao = new FuncionarioDAO();
        funcdao.deletar(func);
    }
    
    @Override
    public Funcionario buscarPorId(Funcionario func) throws NotFoundException {
        funcdao = new FuncionarioDAO();
        func = funcdao.buscarPorId(func);
        if (func != null) {
            return func;
        }
        throw new NotFoundException("Funcionário não encontrado");
    }
    
    public Funcionario buscarPorLogin(Funcionario func) {
        funcdao = new FuncionarioDAO();
        func = funcdao.buscarPorLogin(func);
        if (func != null) {
            return func;
        }
        return null;
    }
    
    public ArrayList<String> listarNomesResponsavel() {
        funcdao = new FuncionarioDAO();
        return funcdao.listarNomesResponsavel();
    }
    
    @Override
    public List<Funcionario> listar() {
        funcdao = new FuncionarioDAO();
        return funcdao.listar();
    }
    
}
