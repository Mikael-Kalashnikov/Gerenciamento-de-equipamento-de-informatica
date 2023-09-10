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
	
	public void deletar(Equipamento eq){}
	public void alterar(Equipamento eq){}
	public Cliente buscar(Equipamento eq){
		return null;
	}
	public List<Equipamento> listar(){
		return null;
	}
}
