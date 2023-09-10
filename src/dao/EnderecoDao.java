package dao;

import java.sql.*;
import model.entity.Endereco;
import java.util.List;

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
	
	public void deletar(Endereco end){}
	public void alterar(Endereco end){}
	public Endereco buscar(Endereco end){
		return null;
	}
	public List<Endereco> listar(){
		return null;
	}
}
