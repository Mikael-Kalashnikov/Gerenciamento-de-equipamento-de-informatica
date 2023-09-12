package dao;

import java.sql.*;
import model.entity.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalDao extends BaseDaoImp<Local> {
	
	public Long inserir(Local loc) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO local (nome, nomeCompartimento) values (?, ?)";
		String nome = loc.getNome();
		String nomeCompartimento = loc.getNomeCompartimento();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, nomeCompartimento);
			ps.execute();
			ps.close();
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
        String sql = "UPDATE local SET nome = ?, nomeCompartimento = ? WHERE id_local = ?";
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
                local = new Local();
                local.setId(rs.getLong(1));
                local.setNome(rs.getString(2));
                local.setNomeCompartimento(rs.getString(3));
            }
		} catch (SQLException e) {
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
                Local loc = new Local(rs.getLong(1), rs.getString(2), rs.getString(3));
                lista.add(loc);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return lista;
	}
}
