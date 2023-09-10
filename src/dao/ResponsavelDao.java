package dao;

import java.sql.*;
import model.entity.Responsavel;
import java.util.List;

public class ResponsavelDao extends BaseDaoImp<Responsavel> {
	
	public Long inserir(Responsavel resp) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO responsavel (nome, login, senha, endereco, telefone) values (?, ?, ?, ?, ?)";
		String nome = resp.getNome();
		String login = resp.getLogin();
		String senha = resp.getSenha();
		String endereco = resp.getEndereco();
		String telefone = resp.getTelefone();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, login);
			ps.setString(3, senha);
			ps.setString(4, endereco);
			ps.setString(5, telefone);
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
	
	public void deletar(Responsavel resp){
        Connection con = BaseDaoImp.getConnection();
        String rua = resp.getId();
        String sql = "DELETE FROM responsavel WHERE id_responsavel = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id_responsavel);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }


	public void alterar(Responsavel func){
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE responsavel SET nome = ?, login = ?, senha = ?, endereco = ?, telefone = ? WHERE id_responsavel = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, func.getNome());
            ps.setString(2, func.getLogin());
            ps.setString(3, func.getSenha());
            ps.setString(4, func.getEndereco());
            ps.setString(5, func.getTelefone());
            ps.setLong(6, func.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public Responsavel buscar(Responsavel func){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM responsavel WHERE id_responsavel = ?";
        Responsavel responsavel = null;
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, func.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                responsavel = new Responsavel();
                responsavel.setId(rs.getLong(1));
                responsavel.setNome(rs.getString(2));
                responsavel.setLogin(rs.getString(3));
                responsavel.setEndereco(rs.getString(4));
                responsavel.setTelefone(rs.getString(5));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return responsavel;
	}

	public List<Responsavel> listar(){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM responsavel";
        List<Responsavel> lista = new ArrayList<>();
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Responsavel func = new Responsavel(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                lista.add(func);
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
