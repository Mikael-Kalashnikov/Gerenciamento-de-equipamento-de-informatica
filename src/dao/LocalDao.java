package dao;

import java.sql.*;
import model.entity.Local;
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
	
	public void deletar(Local loc){}
	public void alterar(Local loc){}
	public Endereco buscar(Local loc){
		return null;
	}
	public List<Local> listar(){
		return null;
	}
}
