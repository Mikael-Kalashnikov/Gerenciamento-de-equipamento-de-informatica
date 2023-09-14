package br.edu.ufersa.MinhaCasaTech.src.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import br.edu.ufersa.MinhaCasaTech.src.model.entity.Venda;

public class VendaDao extends BaseDaoImp<Venda> {
	
	public Long inserir(Venda vend) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO vendas (cliente, equipamento) values (?, ?)";
		//int cliente = vend.getCliente();
		//int equipamento = vend.getEquipamentos();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, cliente);
			//ps.setString(2, equipamento);
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
	
	public void deletar(Venda vend){
        Connection con = BaseDaoImp.getConnection();
        //String rua = vend.getId();
        String sql = "DELETE FROM vendas WHERE id_vendas = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setLong(1, id_vendas);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public void alterar(Venda vend){}
	public Venda buscar(Venda vend){
		return null;
	}
	public List<Venda> listar(){
        List<Venda> vendas = new ArrayList<>();
        vendas.add(0, null);
		return null;
	}
}
