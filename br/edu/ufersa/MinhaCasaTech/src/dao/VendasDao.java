package dao;

import java.sql.*;
import model.entity.Vendas;
import java.util.List;

public class VendasDao extends BaseDaoImp<Vendas> {
	
	public Long inserir(Vendas vend) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO vendas (cliente, equipamento) values (?, ?)";
		int cliente = vend.getCliente();
		int equipamento = vend.getEquipamentos();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cliente);
			ps.setString(2, equipamento);
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
	
	public void deletar(Vendas vend){
        Connection con = BaseDaoImp.getConnection();
        String rua = vend.getId();
        String sql = "DELETE FROM vendas WHERE id_vendas = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id_vendas);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }

	public void alterar(Vendas vend){}
	public Cliente buscar(Vendas vend){
		return null;
	}
	public List<Vendas> listar(){
		return null;
	}
}
