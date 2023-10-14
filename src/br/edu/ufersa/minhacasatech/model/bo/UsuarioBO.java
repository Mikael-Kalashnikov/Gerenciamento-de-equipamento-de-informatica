package br.edu.ufersa.minhacasatech.model.bo;

import br.edu.ufersa.minhacasatech.exception.AutenticationException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.exception.NotFoundException;
import br.edu.ufersa.minhacasatech.model.dao.UsuarioDAO;
import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import java.util.List;

public class UsuarioBO implements BaseBO<Usuario> {
    
    private UsuarioDAO usudao;
    
    public Usuario autenticar(Usuario usu) throws AutenticationException {
        usudao = new UsuarioDAO();
        Usuario usuario = usudao.buscarPorLogin(usu);
        if (usuario != null && usu.getSenha().equals(usuario.getSenha())) {
            return usuario;
        }
        throw new AutenticationException("Usuario e/ou senha incorretos");
    }
    
    @Override
    public void cadastrar(Usuario usu) throws InvalidInsertException {
        usudao = new UsuarioDAO();
        usu.setId(usudao.inserir(usu));
    }
    
    @Override
    public void alterar(Usuario usu) throws InvalidInsertException {
        usudao = new UsuarioDAO();
    }

    @Override
    public void remover(Usuario usu) {
        usudao = new UsuarioDAO();
    }

    @Override
    public Usuario buscarPorId(Usuario usu) throws NotFoundException {
        usudao = new UsuarioDAO();
        return null;
    }
    
    public Usuario buscarPorLogin(Usuario usu) throws NotFoundException, AutenticationException {
        usudao = new UsuarioDAO();
        usu = usudao.buscarPorLogin(usu);
        if (usu != null) {
            return usu;
        }
        throw new NotFoundException("Usuario nao encontrado");
    }
    
    @Override
    public List<Usuario> listar() {
        usudao = new UsuarioDAO();
        return null;
    }
    
}
