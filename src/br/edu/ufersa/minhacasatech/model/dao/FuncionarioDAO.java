package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;

public class FuncionarioDAO extends BaseDAOImp<Funcionario> {

	@Override
	public Long inserir(Funcionario func) {
		Connection con = BaseDAOImp.getConnection();
		String sql = "INSERT INTO funcionario (nome, login, senha, endereco, telefone, salario) values (?, ?, ?, ?, ?, ?)";
		Long id = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, func.getNome());
			ps.setString(2, func.getLogin());
			ps.setString(3, func.getSenha());
			ps.setLong(4, func.getEndereco().getId());
			ps.setString(5, func.getTelefone());
			ps.setDouble(6, func.getSalario());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong("id");
			}

			JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void deletar(Funcionario func) {
		Connection con = BaseDAOImp.getConnection();
		String sql = "DELETE FROM funcionario WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, func.getId());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Funcionário deletado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Funcionario func) {
		Connection con = BaseDAOImp.getConnection();
		String sql = "UPDATE funcionario SET nome = ?, login = ?, senha = ?, endereco = ?, telefone = ?, salario = ? WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, func.getNome());
			ps.setString(2, func.getLogin());
			ps.setString(3, func.getSenha());
			ps.setLong(4, func.getEndereco().getId());
			ps.setString(5, func.getTelefone());
			ps.setDouble(6, func.getSalario());
			ps.setLong(7, func.getId());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Dados editado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Funcionario buscar(Funcionario func) {
		String sql = "SELECT * FROM funcionario WHERE id = ?";
		Funcionario funcionario = null;
		Endereco end;
		EnderecoDAO endao;
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, func.getId());
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				funcionario = new Funcionario(rs.getString("login"), rs.getString("senha"), rs.getDouble("salario"));
				funcionario.setNome(rs.getString("nome"));

				end = new Endereco();
				end.setId(rs.getLong("endereco"));
				endao = new EnderecoDAO();
				end = endao.buscar(end);

				funcionario.setEndereco(end);
				funcionario.setTelefone(rs.getString("telefone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	@Override
	public List<Funcionario> listar() {
		String sql = "SELECT * FROM funcionario";
		List<Funcionario> lista = new ArrayList<>();
		Endereco end;
		EnderecoDAO endao;
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				endao = new EnderecoDAO();
				end = new Endereco();
				end.setId(rs.getLong("endereco"));
				end = endao.buscar(end);

				Funcionario func = new Funcionario(rs.getString("login"), rs.getString("senha"),
						rs.getDouble("salario"));
				func.setNome(rs.getString("nome"));
				func.setEndereco(end);
				func.setTelefone(rs.getString("telefone"));
				rs.getDouble("salario");

				lista.add(func);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
