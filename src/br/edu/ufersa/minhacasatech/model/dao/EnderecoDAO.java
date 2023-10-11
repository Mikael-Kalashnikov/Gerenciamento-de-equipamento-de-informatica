package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class EnderecoDAO extends BaseDAOImp<Endereco> {

	@Override
	public Long inserir(Endereco end) {
		String sql = "INSERT INTO endereco (rua, numero) VALUES (?, ?)";
		Long id = null;
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, end.getRua());
			ps.setInt(2, end.getNumero());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
			}

			JOptionPane.showMessageDialog(null, "Endereço cadastrado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void deletar(Endereco end) {
		String sql = "DELETE FROM endereco WHERE id = ?";
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, end.getId());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Endereço deletado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Endereco end) {
		String sql = "UPDATE endereco SET rua = ?, numero = ? WHERE id = ?";
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, end.getRua());
			ps.setInt(2, end.getNumero());
			ps.setLong(3, end.getId());
			ps.execute();

			JOptionPane.showMessageDialog(null, "Dados editado com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Endereco buscar(Endereco end) {
		String sql = "SELECT * FROM endereco WHERE id = ?";
		Endereco endereco = null;
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, end.getId());
			ps.execute();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				endereco = new Endereco(rs.getString(2), rs.getInt(3));
				endereco.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endereco;
	}

	@Override
	public List<Endereco> listar() {
		String sql = "SELECT * FROM endereco";
		List<Endereco> lista = new ArrayList<>();
		try {
			Connection con = BaseDAOImp.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Endereco end = new Endereco(rs.getString(2), rs.getInt(3));
				end.setId(rs.getLong(1));
				lista.add(end);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
