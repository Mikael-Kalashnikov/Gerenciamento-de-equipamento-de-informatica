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

	
	public void deletar(Cliente cli){
        Connection con = BaseDaoImp.getConnection();
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, cli.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }
	public void alterar(Cliente cli){
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, cpf = ? WHERE id_cliente = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cli.getNome());
            ps.setString(2, cli.getEndereco());
            ps.setString(3, cli.getCpf());
            ps.setString(4, cli.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
    }
	public Cliente buscar(Cliente cli){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, cli.getId());
			ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong(1));
                cliente.setNome(rs.getString(2));
                cliente.setEndereco(rs.getString(3));
                cliente.setCpf(rs.getString(4));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
		return cliente;
	}
	public List<Cliente> listar(){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
                lista.add(cli);
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
