package dao;

import java.sql.*;
import model.entity.Equipamento;
import java.util.List;

public class EquipamentoDao extends BaseDaoImp<Equipamento> {
	
	public Long inserir(Equipamento eq) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO equipamento (nome, endereco, cpf) values (?, ?, ?)";
		String nome = eq.getNome();
		String endereco = eq.getEndereco();
		String cpf = eq.getCpf();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, endereco);
			ps.setString(3, cpf);
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
        String rua = eq.getId();
        String sql = "DELETE FROM equipamento WHERE id_equipamento = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id_equipamento);
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
            ps.setString(2, eq.getEndereco());
            ps.setString(3, eq.getCpf());
            ps.setLong(4, eq.getId());
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
			ps.setLong(1, eq.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                equipamento = new Equipamento();
                equipamento.setId(rs.getLong(1));
                equipamento.setNome(rs.getString(2));
                equipamento.setEndereco(rs.getString(3));
                equipamento.setCpf(rs.getString(4));
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
                Equipamento eq = new Equipamento(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
                lista.add(eq);
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
