package br.edu.ufersa.MinhaCasaTech.src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.MinhaCasaTech.src.model.entity.Equipamento;

public class EquipamentoDao extends BaseDaoImp<Equipamento> {
	
	public Long inserir(Equipamento eq) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO equipamento (nome, endereco, cpf) values (?, ?, ?)";
		String nome = eq.getNome();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
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
	
	public void deletar(Equipamento eq){
        Connection con = BaseDaoImp.getConnection();
        String sql = "DELETE FROM equipamento WHERE id_equipamento = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    	}

	public void alterar(Equipamento eq){
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE equipamento SET nome = ?, endereco = ?, cpf = ? WHERE id_equipamento = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, eq.getNome());
            
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }
	public Equipamento buscar(Equipamento eq){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM equipamento WHERE id_equipamento = ?";
        Equipamento equipamento = null;
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setLong(1, eq.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                equipamento = new Equipamento();
                
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return equipamento;
	}
	public List<Equipamento> listar(){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM equipamento";
        List<Equipamento> lista = new ArrayList<>();
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Equipamento eq = new Equipamento(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //lista.add(eq);
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
