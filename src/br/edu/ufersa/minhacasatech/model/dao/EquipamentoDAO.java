package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO extends BaseDAOImp<Equipamento> {

    @Override
    public Long inserir(Equipamento eq) {
	String sql = "INSERT INTO equipamento (nome, numserie, preco, quantidade, local, responsavel) values (?, ?, ?, ?, ?, ?)";
	Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setString(1, eq.getNome());
	    ps.setString(2, eq.getNumSerie());
	    ps.setDouble(3, eq.getPreco());
	    ps.setInt(4, eq.getQuantidade());
	    ps.setLong(5, eq.getLocal().getId());
	    ps.setLong(6, eq.getResponsavel().getId());
	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) {
		id = rs.getLong(1);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return id;
    }

    @Override
    public void deletar(Equipamento eq){
	String sql = "DELETE FROM equipamento WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void alterar(Equipamento eq){
	String sql = "UPDATE equipamento SET nome = ?, numserie = ?, preco = ?, quantidade = ?, local = ?, responsavel = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, eq.getNome());
	    ps.setString(2, eq.getNumSerie());
	    ps.setDouble(3, eq.getPreco());
	    ps.setInt(4, eq.getQuantidade());
	    ps.setLong(5, eq.getLocal().getId());
	    ps.setLong(6, eq.getResponsavel().getId());
	    ps.setLong(7, eq.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public Equipamento buscarPorId(Equipamento eq){
	String sql = "SELECT * FROM equipamento WHERE id = ?";
	Equipamento equipamento = null;
	Local local = new Local();
	Funcionario responsavel = new Funcionario();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, eq.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		local.setId(rs.getLong("local"));
		responsavel.setId(rs.getLong("responsavel"));
		
		LocalDAO locdao = new LocalDAO();
		FuncionarioDAO funcdao = new FuncionarioDAO();
		local = locdao.buscarPorId(local);
		responsavel = funcdao.buscarPorId(responsavel);
		
		equipamento = new Equipamento(rs.getString("nome"), rs.getString("numserie"), rs.getDouble("preco"), rs.getInt("quantidade"), local, responsavel);
		equipamento.setId(rs.getLong("id"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return equipamento;
    }

    @Override
    public List<Equipamento> listar(){
	String sql = "SELECT * FROM equipamento";
	List<Equipamento> lista = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Local local = new Local();
		Funcionario responsavel = new Funcionario();
		local.setId(rs.getLong("local"));
		responsavel.setId(rs.getLong("responsavel"));
		
		LocalDAO locdao = new LocalDAO();
		FuncionarioDAO funcdao = new FuncionarioDAO();
		local = locdao.buscarPorId(local);
		responsavel = funcdao.buscarPorId(responsavel);
		
		Equipamento equipamento = new Equipamento(rs.getString("nome"), rs.getString("numserie"), rs.getDouble("preco"), rs.getInt("quantidade"), local, responsavel);
		equipamento.setId(rs.getLong("id"));
		lista.add(equipamento);
	    }
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return lista;
    }
}
