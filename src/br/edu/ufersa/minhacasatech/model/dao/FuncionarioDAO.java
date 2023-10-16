package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDAO extends BaseDAOImp<Funcionario> {
    
    @Override
    public Long inserir(Funcionario func) {
	String sql = "INSERT INTO funcionario (nome, login, senha, telefone, cpf, endereco) values (?, ?, ?, ?, ?, ?)";
	Long id = null;
	try {
            Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setString(1, func.getNome());
	    ps.setString(2, func.getLogin());
	    ps.setString(3, func.getSenha());
	    ps.setString(4, func.getTelefone());
	    ps.setString(5, func.getCpf());
            ps.setString(6, func.getEndereco());
	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("id");
            }
	} catch (SQLException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return id;
    }
    
    @Override
    public void deletar(Funcionario func){
	String sql = "DELETE FROM funcionario WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, func.getId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
    }
    
    @Override
    public void alterar(Funcionario func){
	String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, telefone = ?, cpf = ?, endereco = ?, is_responsavel = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, func.getNome());
	    ps.setString(2, func.getLogin());
	    ps.setString(3, func.getSenha());
	    ps.setString(4, func.getTelefone());
            ps.setString(5, func.getCpf());
            ps.setString(6, func.getEndereco());
            ps.setBoolean(7, func.getIsResponsavel());
	    ps.setLong(8, func.getId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
    }
    
    @Override
    public Funcionario buscarPorId(Funcionario func) {
	String sql = "SELECT * FROM funcionario WHERE id = ?";
	Funcionario funcionario = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, func.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		funcionario = new Funcionario(rs.getString("login"), rs.getString("senha"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setEndereco(rs.getString("endereco"));
		funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setIsResponsavel(rs.getBoolean("is_responsavel"));
                funcionario.setDataCadastro(rs.getDate("data_cadastro"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return funcionario;
    }
    
    public Funcionario buscarPorLogin(Funcionario func) {
        String sql = "SELECT * FROM funcionario WHERE login = ?";
        Funcionario funcionario = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, func.getLogin());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
                funcionario = new Funcionario();
		funcionario.setId(rs.getLong("id"));
		funcionario.setNome(rs.getString("nome"));
                funcionario.setLogin(rs.getString("login"));
                funcionario.setSenha(rs.getString("senha"));
		funcionario.setEndereco(rs.getString("endereco"));
		funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setIsResponsavel(rs.getBoolean("is_responsavel"));
                funcionario.setDataCadastro(rs.getDate("data_cadastro"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return funcionario;
    }
    
    @Override
    public List<Funcionario> listar(){
        String sql = "SELECT * FROM funcionario ORDER BY id";
        List<Funcionario> lista = new ArrayList<>();
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario func = new Funcionario(rs.getString("login"), rs.getString("senha"));
                func.setId(rs.getLong("id"));
		func.setNome(rs.getString("nome"));
                func.setLogin(rs.getString("login"));
                func.setSenha(rs.getString("senha"));
		func.setEndereco(rs.getString("endereco"));
		func.setTelefone(rs.getString("telefone"));
                func.setCpf(rs.getString("cpf"));
                func.setIsResponsavel(rs.getBoolean("is_responsavel"));
                func.setDataCadastro(rs.getDate("data_cadastro"));
                
                lista.add(func);
            }
        } catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            BaseDAOImp.closeConnection();
        }
        return lista;
    }
    
}
