package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDAO extends BaseDAOImp<Endereco> {

    @Override
    public Long inserir(Endereco end) {
	String sql = "INSERT INTO endereco (rua, numero) VALUES (?, ?)";
	Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setString(1, end.getRua());
	    ps.setInt(2, end.getNumero());
	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) {
		id = rs.getLong(1);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return id;
    }

    @Override
    public void deletar(Endereco end) {
	String sql = "DELETE FROM endereco WHERE id = ?";
        try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, end.getId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
    }

    @Override
    public void alterar(Endereco end) {
        String sql = "UPDATE endereco SET rua = ?, numero = ? WHERE id = ?";
        try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, end.getRua());
	    ps.setInt(2, end.getNumero());
	    ps.setLong(3, end.getId());
	    ps.execute();
	} catch (SQLException ex) {
	    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
    }

    @Override
    public Endereco buscarPorId(Endereco end) {
        String sql = "SELECT * FROM endereco WHERE id = ?";
        Endereco endereco = null;
        try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, end.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		endereco = new Endereco(rs.getString("rua"), rs.getInt("numero"));
		endereco.setId(rs.getLong("id"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return endereco;
    }
    
    public Endereco buscarPorRua(Endereco end) {
        String sql = "SELECT * FROM endereco WHERE rua ILIKE ?";
        Endereco endereco = null;
        try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, end.getRua());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
		endereco = new Endereco(rs.getString("rua"), rs.getInt("numero"));
		endereco.setId(rs.getLong("id"));
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return endereco;
    }
    
    @Override
    public List<Endereco> listar() {
	String sql = "SELECT * FROM endereco";
	List<Endereco> lista = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		Endereco end = new Endereco(rs.getString("rua"), rs.getInt("numero"));
		end.setId(rs.getLong("id"));
		lista.add(end);
	    }
	} catch (SQLException | InvalidInsertException ex) {
	    Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return lista;
    }
}
