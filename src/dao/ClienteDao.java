package dao;

import java.sql.*;
import model.entity.Cliente;
import java.util.List;

public class ClienteDao extends BaseDaoImp<Cliente> {
	
	public Long inserir(Cliente cli) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO cliente (nome, endereco, cpf) values (?, ?, ?)";
		String nome = cli.getNome();
		String endereco = cli.getEndereco();
		String cpf = cli.getCpf();
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
	
	public void deletar(Cliente cli){}
	public void alterar(Cliente cli){}
	public Cliente buscar(Cliente cli){
		return null;
	}
	public List<Cliente> listar(){
		return null;
	}
}
