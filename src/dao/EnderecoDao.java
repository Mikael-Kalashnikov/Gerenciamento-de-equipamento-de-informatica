package dao;

import java.sql.*;
import model.entity.Endereco;
import java.util.List;
import java.util.ArrayList;

public class EnderecoDao extends BaseDaoImp<Endereco> {
	
	public Long inserir(Endereco end) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO endereco (rua, numero) values (?, ?)";
		String rua = end.getRua();
		int numero = end.getNumero();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rua);
			ps.setInt(2, numero);
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
	
	public void deletar(Endereco end) {
        Connection con = BaseDaoImp.getConnection();
        String rua = end.getRua();
        String sql = "DELETE FROM endereco WHERE rua = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rua);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public void alterar(Endereco end) {
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE endereco SET rua = ?, numero = ? WHERE id_endereco = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, end.getRua());
            ps.setInt(2, end.getNumero());
            ps.setLong(3, end.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public Endereco buscar(Endereco end) {
        Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        Endereco endereco = null;
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, end.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                endereco = new Endereco();
                endereco.setId(rs.getLong(1));
                endereco.setRua(rs.getString(2));
                endereco.setNumero(rs.getInt(3));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return endereco;
	}

	public List<Endereco> listar() {
        Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM endereco";
        List<Endereco> lista = new ArrayList<>();
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Endereco end = new Endereco(rs.getLong(1), rs.getString(2), rs.getInt(3));
                lista.add(end);
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
