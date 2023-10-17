package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO extends BaseDAOImp<Venda> {
    
    @Override
    public Long inserir(Venda venda) {
	String sql = "INSERT INTO venda (cliente, status, funcionario, equipamento) values (?, ?, ?, ?)";
        Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.setString(2, venda.getStatus());
            ps.setLong(3, venda.getFuncionario().getId());
            ps.setLong(4, venda.getEquipamento().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("id");
            }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return id;
    }
    
    @Override
    public void deletar(Venda venda) {
	String sql = "DELETE FROM venda WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public void alterar(Venda venda) {
	String sql = "UPDATE venda SET cliente = ?, equipamento = ?, funcionario = ?, status = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.setLong(2, venda.getEquipamento().getId());
	    ps.setLong(3, venda.getFuncionario().getId());
	    ps.setString(4, venda.getStatus());
	    ps.setLong(5, venda.getCliente().getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public Venda buscarPorId(Venda venda) {
	String sql = "SELECT * FROM venda WHERE id = ?";
	Venda vendaEncontrada = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
                EquipamentoDAO eqdao = new EquipamentoDAO();
                Equipamento eq = new Equipamento();
                eq.setId(rs.getLong("equipamento"));
                eq = eqdao.buscarPorId(eq);
                
                FuncionarioDAO funcdao = new FuncionarioDAO();
                Funcionario func = new Funcionario();
                func.setId(rs.getLong("funcionario"));
                func = funcdao.buscarPorId(func);
                
                ClienteDAO clidao = new ClienteDAO();
                Cliente cli = new Cliente();
                cli.setId(rs.getLong("cliente"));
                cli = clidao.buscarPorId(cli);
                
		vendaEncontrada = new Venda();
		vendaEncontrada.setCliente(cli);
		vendaEncontrada.setEquipamento(eq);
		vendaEncontrada.setFuncionario(func);
		vendaEncontrada.setStatus(rs.getString("status"));
                vendaEncontrada.setDataVenda(rs.getDate("data_cadastro"));
                vendaEncontrada.setId(rs.getLong("id"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (InvalidInsertException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	return vendaEncontrada;
    }
    
    @Override
    public List<Venda> listar() {
	String sql = "SELECT * FROM venda ORDER BY id";
	List<Venda> listaVendas = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		    EquipamentoDAO eqdao = new EquipamentoDAO();
                Equipamento eq = new Equipamento();
                eq.setId(rs.getLong("equipamento"));
                eq = eqdao.buscarPorId(eq);
                
                FuncionarioDAO funcdao = new FuncionarioDAO();
                Funcionario func = new Funcionario();
                func.setId(rs.getLong("funcionario"));
                func = funcdao.buscarPorId(func);
                
                ClienteDAO clidao = new ClienteDAO();
                Cliente cli = new Cliente();
                cli.setId(rs.getLong("cliente"));
                cli = clidao.buscarPorId(cli);
                
		Venda vendaEncontrada = new Venda();
		vendaEncontrada.setCliente(cli);
		vendaEncontrada.setEquipamento(eq);
		vendaEncontrada.setFuncionario(func);
		vendaEncontrada.setStatus(rs.getString("status"));
                vendaEncontrada.setDataVenda(rs.getDate("data_cadastro"));
                vendaEncontrada.setId(rs.getLong("id"));
		listaVendas.add(vendaEncontrada);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (InvalidInsertException e) {
	    e.printStackTrace();
	}
	return listaVendas;
    }
}
