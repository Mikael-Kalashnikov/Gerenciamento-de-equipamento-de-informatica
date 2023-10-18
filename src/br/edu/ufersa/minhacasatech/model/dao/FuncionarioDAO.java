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
	String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, telefone = ?, cpf = ?, endereco = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, func.getNome());
	    ps.setString(2, func.getLogin());
	    ps.setString(3, func.getSenha());
	    ps.setString(4, func.getTelefone());
            ps.setString(5, func.getCpf());
            ps.setString(6, func.getEndereco());
	    ps.setLong(7, func.getId());
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
		funcionario = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("endereco"));
                funcionario.setId(rs.getLong("id"));
                funcionario.setIsResponsavel(rs.getBoolean("is_responsavel"));
                funcionario.setTotalVendas(rs.getDouble("total_vendas"));
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
                funcionario = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("endereco"));
                funcionario.setId(rs.getLong("id"));
                funcionario.setIsResponsavel(rs.getBoolean("is_responsavel"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return funcionario;
    }
    
    public Funcionario buscarPorNome(Funcionario func) {
        String sql = "SELECT * FROM funcionario WHERE nome = ?";
        Funcionario funcionario = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, func.getNome());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
                funcionario = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("endereco"));
                funcionario.setId(rs.getLong("id"));
                funcionario.setIsResponsavel(rs.getBoolean("is_responsavel"));
                funcionario.setTotalVendas(rs.getDouble("total_vendas"));
                funcionario.setDataCadastro(rs.getDate("data_cadastro"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return funcionario;
    }
    
    public ArrayList<String> listarNomesResponsavel() {
        String sql = "SELECT * FROM nomes_responsaveis"; // view
        ArrayList<String> nomes = new ArrayList<>();
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                nomes.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomes;
    }
    
    public ArrayList<String> listarNomesFuncionario() {
        String sql = "SELECT nome FROM funcionario ORDER BY nome"; // view
        ArrayList<String> nomes = new ArrayList<>();
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                nomes.add(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nomes;
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
                Funcionario funcionario = new Funcionario(rs.getString("nome"), rs.getString("login"), rs.getString("senha"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("endereco"));
                funcionario.setId(rs.getLong("id"));
                funcionario.setIsResponsavel(rs.getBoolean("is_responsavel"));
                funcionario.setTotalVendas(rs.getDouble("total_vendas"));
                funcionario.setDataCadastro(rs.getDate("data_cadastro"));
                
                lista.add(funcionario);
            }
        } catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            BaseDAOImp.closeConnection();
        }
        return lista;
    }
    
}
