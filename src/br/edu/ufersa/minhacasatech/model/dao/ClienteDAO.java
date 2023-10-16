package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO extends BaseDAOImp<Cliente> {
    
    @Override
    public Long inserir(Cliente cli) {
	String sql = "INSERT INTO cliente (nome, cpf, telefone, endereco) values (?, ?, ?, ?)";
	Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getTelefone());
            ps.setString(4, cli.getEndereco());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("id");
            }
	} catch (SQLException ex) {
	    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return id;
    }

    @Override
    public void deletar(Cliente cli){
        String sql = "DELETE FROM cliente WHERE id = ?";
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, cli.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void alterar(Cliente cli)  {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, endereco = ? WHERE id = ?";
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNome());
            ps.setString(2, cli.getCpf());
            ps.setString(3, cli.getTelefone());
            ps.setString(4, cli.getEndereco());
            ps.setLong(5, cli.getId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Cliente buscarPorId(Cliente cli){
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente cliente = null;
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, cli.getId());
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // atribui os dados de cliente
                cliente = new Cliente(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"),  rs.getString("endereco"));
                cliente.setDataCadastro(rs.getDate("data_cadastro"));
                cliente.setId(rs.getLong("id"));
            }
        } catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    public Cliente buscarPorCpf(Cliente cli) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        Cliente cliente = null;
        try {
            Connection con = BaseDAOImp.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cli.getCpf());
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // atribui os dados de cliente
                cliente = new Cliente(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("endereco"));
                cliente.setDataCadastro(rs.getDate("data_cadastro"));
                cliente.setId(rs.getLong("id"));
            }
        } catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    @Override
    public List<Cliente> listar(){
	String sql = "SELECT * FROM cliente ORDER BY id";
	List<Cliente> lista = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		// Instancia um novo objeto cliente com o endereco associado
		Cliente cli = new Cliente(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"), rs.getString("endereco"));
                cli.setDataCadastro(rs.getDate("data_cadastro"));
		cli.setId(rs.getLong("id"));
		
		// Adiciona o cliente na lista
		lista.add(cli);
	    }
        } catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	return lista;
    }
}
