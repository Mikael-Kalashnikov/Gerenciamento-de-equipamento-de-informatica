package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO extends BaseDAOImp<Venda> {

    @Override
    public Long inserir(Venda venda) {
	String sql = "INSERT INTO venda (cliente, equipamento, data_venda, valor_total) values (?, ?, ?, ?)";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.setDate(3, venda.getDataVenda());
	    ps.setDouble(4, venda.getValorTotal());
	    ps.execute();
	    
	    List<Equipamento> equipamentos = venda.getEquipamentos();
	    for (Equipamento eq : equipamentos) {
		ps.setLong(2, eq.getId());
		ps.execute();
	    }
	    ps.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public void deletar(Venda venda) {
	String sql = "DELETE FROM venda WHERE cliente = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void alterar(Venda venda) {
	String sql = "UPDATE venda SET cliente = ?, equipamento = ?, data_venda = ?, valor_total = ? WHERE cliente = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.setLong(2, venda.getEquipamentos().get(0).getId());
	    ps.setDate(3, venda.getDataVenda());
	    ps.setDouble(4, venda.getValorTotal());
	    ps.setLong(5, venda.getCliente().getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public Venda buscarPorId(Venda venda) {
	String sql = "SELECT * FROM venda WHERE cliente = ?";
	Venda vendaEncontrada = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		vendaEncontrada = new Venda();
		vendaEncontrada.getCliente().setId(rs.getLong("cliente"));
		vendaEncontrada.getEquipamentos().get(0).setId(rs.getLong("equipamento"));
		vendaEncontrada.setDataVenda(rs.getDate("data_venda"));
		vendaEncontrada.setValorTotal(rs.getDouble("valor_total"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return vendaEncontrada;
    }

    @Override
    public List<Venda> listar() {
	String sql = "SELECT * FROM venda ORDER BY id";
	List<Venda> listaVendas = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		    Venda venda = new Venda();
		    venda = new Venda();
		    venda.getCliente().setId(rs.getLong("cliente"));
		    venda.getEquipamentos().get(0).setId(rs.getLong("equipamento"));
		    venda.setDataVenda(rs.getDate("data_venda"));
		    venda.setValorTotal(rs.getDouble("valor_total"));
		    listaVendas.add(venda);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return listaVendas;
    }
}
