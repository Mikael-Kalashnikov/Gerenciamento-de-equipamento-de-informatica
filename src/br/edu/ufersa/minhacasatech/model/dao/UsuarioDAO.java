package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO extends BaseDAOImp<Usuario> {
    
    @Override
    public Long inserir(Usuario usu) {
	String sql = "INSERT INTO usuario (nome, login, senha, endereco, telefone, tipo) values (?, ?, ?, ?, ?, ?)";
	Long id = null;
	try {
            Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setString(1, usu.getNome());
	    ps.setString(2, usu.getLogin());
	    ps.setString(3, usu.getSenha());
	    ps.setLong(4, usu.getEndereco().getId());
	    ps.setString(5, usu.getTelefone());
            ps.setString(6, usu.getTipo());
	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("id");
            }
	} catch (SQLException ex) {
	    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return id;
    }
    
    @Override
    public void deletar(Usuario usu){
	String sql = "DELETE FROM usuario WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, usu.getId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
    }
    
    @Override
    public void alterar(Usuario usu){
	String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, endereco = ?, telefone = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, usu.getNome());
	    ps.setString(2, usu.getLogin());
	    ps.setString(3, usu.getSenha());
	    ps.setLong(4, usu.getEndereco().getId());
	    ps.setString(5, usu.getTelefone());
	    ps.setLong(6, usu.getId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
    }
    
    @Override
    public Usuario buscarPorId(Usuario usu) {
	String sql = "SELECT * FROM usuario WHERE id = ?";
	Usuario usuario = null;
	Endereco end;
	EnderecoDAO endao;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, usu.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		end = new Endereco();
		end.setId(rs.getLong("endereco"));
		endao = new EnderecoDAO();
		end = endao.buscarPorId(end);
		
		usuario = new Usuario(rs.getString("login"), rs.getString("senha"));
		usuario.setNome(rs.getString("nome"));
		usuario.setEndereco(end);
		usuario.setTelefone(rs.getString("telefone"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return usuario;
    }
    
    public Usuario buscarPorLogin(Usuario usu) {
        String sql = "SELECT * FROM usuario WHERE login = ?";
        Usuario usuario = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, usu.getLogin());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
                Endereco end = new Endereco();
                EnderecoDAO endao = new EnderecoDAO();
                
		end.setId(rs.getLong("endereco"));
		end = endao.buscarPorId(end);
		
                usuario = new Usuario();
		usuario.setId(rs.getLong("id"));
		usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
		usuario.setEndereco(end);
		usuario.setTelefone(rs.getString("telefone"));
                usuario.setTipo(rs.getString("tipo"));
                usuario.setDataCadastro(rs.getDate("data_cadastro"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return usuario;
    }
    
    @Override
    public List<Usuario> listar(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> lista = new ArrayList<>();
        Endereco end;
        EnderecoDAO endao;
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                end = new Endereco();
                endao = new EnderecoDAO();
                end.setId(rs.getLong("endereco"));
                end = endao.buscarPorId(end);
                
                Usuario usu = new Usuario(rs.getString("login"), rs.getString("senha"));
                usu.setNome(rs.getString("nome"));
                usu.setEndereco(end);
                usu.setTelefone(rs.getString("telefone"));
                
                lista.add(usu);
            }
        } catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            BaseDAOImp.closeConnection();
        }
        return lista;
    }
    
}
