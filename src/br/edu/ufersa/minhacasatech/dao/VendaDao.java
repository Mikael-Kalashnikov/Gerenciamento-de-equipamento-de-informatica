package br.edu.ufersa.MinhaCasaTech.src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.MinhaCasaTech.src.model.entity.Venda;

public class VendaDao extends BaseDaoImp<Venda> {

	public Long inserir(Venda venda) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO venda (data_venda, valor_total) values (?, ?)";
		java.util.Date dataVenda = venda.getDataVenda();
		double valorTotal = venda.getValorTotal();
		try {
			PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new java.sql.Date(dataVenda.getTime()));
			ps.setDouble(2, valorTotal);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getLong("id_venda");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDaoImp.closeConnection();
		}
		return null;
	}

	public void deletar(Venda venda) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "DELETE FROM venda WHERE id_venda = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, venda.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDaoImp.closeConnection();
		}
	}

	public void alterar(Venda venda) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "UPDATE venda SET data_venda = ?, valor_total = ? WHERE id_venda = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
			ps.setDouble(2, venda.getValorTotal());
			ps.setLong(3, venda.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDaoImp.closeConnection();
		}
	}

	public Venda buscar(Venda venda) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "SELECT * FROM venda WHERE id_venda = ?";
		Venda vendaEncontrada = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, venda.getId());
			ps.execute();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				vendaEncontrada = new Venda();
				vendaEncontrada.setId(rs.getLong("id_venda"));
				vendaEncontrada.setDataVenda(rs.getDate("data_venda"));
				vendaEncontrada.setValorTotal(rs.getDouble("valor_total"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDaoImp.closeConnection();
		}
		return vendaEncontrada;
	}

	public List<Venda> listar() {
		Connection con = BaseDaoImp.getConnection();
		String sql = "SELECT * FROM venda";
		List<Venda> listaVendas = new ArrayList<>();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getLong("id_venda"));
				venda.setDataVenda(rs.getDate("data_venda"));
				venda.setValorTotal(rs.getDouble("valor_total"));
				listaVendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			BaseDaoImp.closeConnection();
		}
		return listaVendas;
	}
}