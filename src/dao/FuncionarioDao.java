package dao;

import java.sql.*;
import model.entity.Funcionario;
import java.util.List;

public class FuncionarioDao extends BaseDaoImp<Funcionario> {
	
	public Long inserir(Funcionario func) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO funcionario (nome, login, senha, endereco, telefone, salario) values (?, ?, ?, ?, ?, ?)";
		String nome = func.getNome();
		String login = func.getLogin();
		String senha = func.getSenha();
		String endereco = func.getEndereco();
		String telefone = func.getTelefone();
		String salario = func.getSalario();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setString(2, login);
			ps.setString(3, senha);
			ps.setString(4, endereco);
			ps.setString(5, telefone);
			ps.setString(6, salario);
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
	
	public void deletar(Funcionario func){}
	public void alterar(Funcionario func){}
	public Cliente buscar(Funcionario func){
		return null;
	}
	public List<Funcionario> listar(){
		return null;
	}
}
