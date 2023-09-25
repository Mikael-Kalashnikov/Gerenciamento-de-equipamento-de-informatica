package br.edu.ufersa.MinhaCasaTech.src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.MinhaCasaTech.src.model.entity.Local;

public class LocalDao extends BaseDaoImp<Local> {
	
	public Long inserir(Local loc) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO local (nome, nome_compartimento) values (?, ?)";
		String nome = loc.getNome();
		String nomeCompartimento = loc.getNomeCompartimento();
		try {
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, nome);
			ps.setString(2, nomeCompartimento);
			ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getLong("id_local");
    	} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return null;
	}
	
	public void deletar(Local loc){
        Connection con = BaseDaoImp.getConnection();
        String sql = "DELETE FROM local WHERE id_local = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, loc.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public void alterar(Local loc){
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE local SET nome = ?, nome_compartimento = ? WHERE id_local = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loc.getNome());
            ps.setString(2, loc.getNomeCompartimento());
            ps.setLong(3, loc.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public Local buscar(Local loc){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM local WHERE id_local = ?";
        Local local = null;
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, loc.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                local = new Local(rs.getString(2), rs.getString(3));
                local.setId(rs.getLong(1));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
		finally {
            BaseDaoImp.closeConnection();
		}
		return local;
	}

	public List<Local> listar(){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM local";
        List<Local> lista = new ArrayList<>();
        try {
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
		finally {
            BaseDaoImp.closeConnection();
		}
		return lista;
	}
}
