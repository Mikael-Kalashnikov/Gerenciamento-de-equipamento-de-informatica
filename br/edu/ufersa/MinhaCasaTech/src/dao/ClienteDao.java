package br.edu.ufersa.MinhaCasaTech.src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.ufersa.MinhaCasaTech.src.model.entity.Cliente;
import br.edu.ufersa.MinhaCasaTech.src.model.entity.Endereco;

public class ClienteDao extends BaseDaoImp<Cliente> {
	
	public Long inserir(Cliente cli) {
		Connection con = BaseDaoImp.getConnection();
		String sql = "INSERT INTO cliente (nome, cpf, endereco) values (?, ?, ?)";
        Long id = null;
		String nome = cli.getNome();
        String cpf = cli.getCpf();
		Long id_endereco = cli.getEndereco().getId();

        // verifica se o endereco do cliente ja existe
        EnderecoDao endao = new EnderecoDao();
        if (id_endereco == null || endao.buscar(cli.getEndereco()) == null) {
            id_endereco = endao.inserir(cli.getEndereco());
            con = BaseDaoImp.getConnection();
        }

		try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, nome);
			ps.setString(2, cpf);
            ps.setLong(3, id_endereco);
			ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getLong("id_cliente");
            ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
            BaseDaoImp.closeConnection();
		}
        return id;
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

	public void alterar(Cliente cli)  {
        Connection con = BaseDaoImp.getConnection();
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, cpf = ? WHERE id_cliente = ?";
        EnderecoDao endao = new EnderecoDao();

        if (endao.buscar(cli.getEndereco()) != null) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, cli.getNome());
                ps.setLong(2, cli.getEndereco().getId());
                ps.setString(3, cli.getCpf());
                ps.setLong(4, cli.getId());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                BaseDaoImp.closeConnection();
            }
        }
        else {
            System.out.println("Não é possível atualizar um cliente com endereco que não existe");
        }
    }

	public Cliente buscar(Cliente cli){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, cli.getId());
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // pega endereco correspondente
                EnderecoDao endao = new EnderecoDao();
                Endereco end = new Endereco();
                end.setId(rs.getLong("endereco"));
                end = endao.buscar(end);

                // atribui os dados de cliente
                cli = new Cliente(rs.getString("nome"), rs.getString("cpf"), end);
                cli.setId(rs.getLong(1));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            BaseDaoImp.closeConnection();
        }
		return cli;
	}

	public List<Cliente> listar(){
		Connection con = BaseDaoImp.getConnection();
        String sql = "SELECT * FROM cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
			PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Cliente cli = new Cliente(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //lista.add(cli);
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
