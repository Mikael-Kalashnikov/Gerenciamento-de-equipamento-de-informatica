package br.edu.ufersa.minhacasatech.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ufersa.minhacasatech.model.entity.Local;

public class LocalDAO extends BaseDAOImp<Local> {

	@Override
	public Long inserir(Local loc) {
		String sql = "INSERT INTO local (nome, nome_compartimento) values (?, ?)";
		Long id = null;
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, loc.getNome());
			ps.setString(2, loc.getNomeCompartimento());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong("id");
			}

			JOptionPane.showMessageDialog(null, "Local cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void deletar(Local loc) {
		String sql = "DELETE FROM local WHERE id = ?";
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, loc.getId());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Local deletado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Local loc) {
		String sql = "UPDATE local SET nome = ?, nome_compartimento = ? WHERE id = ?";
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loc.getNome());
			ps.setString(2, loc.getNomeCompartimento());
			ps.setLong(3, loc.getId());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Dados editado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Local buscar(Local loc) {
		String sql = "SELECT * FROM local WHERE id = ?";
		Local local = null;
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, loc.getId());
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				local = new Local(rs.getString("nome"), rs.getString("nome_compartimento"));
				local.setId(rs.getLong("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return local;
	}

	@Override
	public List<Local> listar() {
		String sql = "SELECT * FROM local";
		List<Local> lista = new ArrayList<>();
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Local loc = new Local(rs.getString(2), rs.getString(3));
				loc.setId(rs.getLong(1));
				lista.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean existeLocalPorNome(String nome) {
		return false;
	}

	public boolean existeLocalPorId(Long id) {
		return false;
	}

	public void atualizar(Local loc) {
	}

	public Local buscarPorId(int localId) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Local local = null;

		try {
			connection = getConnection();
			String sql = "SELECT * FROM locais WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, localId);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				local = new Local();
				local.setId(resultSet.getInt("id"));
				local.setNome(resultSet.getString("nome"));
				// Preencha outros campos do Local, se necessário
			}
		} finally {
			closeResources(connection, statement, resultSet);
		}

		return local;
	}

	private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
	}
}
