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
	
	public void deletar(Responsavel resp){}
	public void alterar(Responsavel resp){}
	public Cliente buscar(Responsavel resp){
		return null;
	}
	public List<Responsavel> listar(){
		return null;
	}
}
