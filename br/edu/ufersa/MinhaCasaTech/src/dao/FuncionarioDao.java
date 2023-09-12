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
	
	public void deletar(Funcionario func){
        Connection con = BaseDaoImp.getConnection();
        String rua = func.getId();
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id_funcionario);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    	}

	public void alterar(Funcionario func){
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, endereco = ?, telefone = ?, salario = ? WHERE id_funcionario = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, func.getNome());
            ps.setString(2, func.getLogin());
            ps.setString(3, func.getSenha());
            ps.setString(4, func.getEndereco());
            ps.setString(5, func.getTelefone());
            ps.setLong(6, func.getSalario());
            ps.setLong(7, func.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }
	public Funcionario buscar(Funcionario func){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
        Funcionario funcionario = null;
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, func.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId(rs.getLong(1));
                funcionario.setNome(rs.getString(2));
                funcionario.setLogin(rs.getString(3));
                funcionario.setEndereco(rs.getString(4));
                funcionario.setTelefone(rs.getString(5));
                funcionario.setSalario(rs.getString(6));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return funcionario;
	}
	public List<Funcionario> listar(){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> lista = new ArrayList<>();
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Funcionario func = new Funcionario(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
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
