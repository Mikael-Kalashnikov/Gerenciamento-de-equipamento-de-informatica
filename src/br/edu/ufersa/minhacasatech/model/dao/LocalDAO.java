package br.edu.ufersa.minhacasatech.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.minhacasatech.model.entity.Local;

public class LocalDAO extends BaseDAOImp<Local> {
    
    @Override
    public Long inserir(Local loc) {
	String sql = "INSERT INTO local (nome, nome_compartimento) values (?, ?)";
	Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setString(1, loc.getNome());
	    ps.setString(2, loc.getNomeCompartimento());
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
    public void deletar(Local loc){
	String sql = "DELETE FROM local WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, loc.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void alterar(Local loc){
	String sql = "UPDATE local SET nome = ?, nome_compartimento = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, loc.getNome());
	    ps.setString(2, loc.getNomeCompartimento());
	    ps.setLong(3, loc.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public Local buscar(Local loc){
	String sql = "SELECT * FROM local WHERE id = ?";
	Local local = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, loc.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		local = new Local(rs.getString("nome"), rs.getString("nome_compartimento"));
		local.setId(rs.getLong("id"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {	
	    e.printStackTrace();
	}
	return local;
    }

    @Override
    public List<Local> listar(){
	String sql = "SELECT * FROM local";
	List<Local> lista = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Local loc = new Local(rs.getString(2), rs.getString(3));
		loc.setId(rs.getLong(1));
		lista.add(loc);
	    }
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return lista;
    }
}
