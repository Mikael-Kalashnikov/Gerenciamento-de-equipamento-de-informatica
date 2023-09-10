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

	public void alterar(Responsavel resp){}
	public Cliente buscar(Responsavel resp){
		return null;
	}
	public List<Responsavel> listar(){
		return null;
	}
}
