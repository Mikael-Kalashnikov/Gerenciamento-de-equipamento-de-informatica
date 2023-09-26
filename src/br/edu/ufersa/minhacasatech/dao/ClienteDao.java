package br.edu.ufersa.minhacasatech.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends BaseDAOImp<Cliente> {
    
    @Override
    public Long inserir(Cliente cli) {
	String sql = "INSERT INTO cliente (nome, cpf, endereco) values (?, ?, ?)";
	Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    // verifica se o endereco do cliente ja existe
	    EnderecoDAO endao = new EnderecoDAO();
	    Endereco end = new Endereco();
	    end.setId(cli.getEndereco().getId());
	    end = endao.buscar(end);
	    if (end != null) {
		PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, cli.getNome());
		ps.setString(2, cli.getCpf());
		ps.setLong(3, cli.getEndereco().getId());
		ps.execute();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    id = rs.getLong(1);
		}
	    }
	    else {
		throw new InvalidInsertException("Cliente");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return id;
    }

	@Override
	public void deletar(Cliente cli){
        Connection con = BaseDAOImp.getConnection();
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, cli.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	@Override
	public void alterar(Cliente cli)  {
        Connection con = BaseDAOImp.getConnection();
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, cpf = ? WHERE id_cliente = ?";
        EnderecoDAO endao = new EnderecoDAO();

        if (endao.buscar(cli.getEndereco()) != null) {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, cli.getNome());
                ps.setLong(2, cli.getEndereco().getId());
                ps.setString(3, cli.getCpf());
                ps.setLong(4, cli.getId());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public Cliente buscar(Cliente cli){
		Connection con = BaseDAOImp.getConnection();
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, cli.getId());
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // pega endereco correspondente
                EnderecoDAO endao = new EnderecoDAO();
                Endereco end = new Endereco();
                end.setId(rs.getLong("endereco"));
                end = endao.buscar(end);

                // atribui os dados de cliente
                cli = new Cliente(rs.getString("nome"), rs.getString("cpf"), end);
                cli.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	return cli;
    }

    @Override
    public List<Cliente> listar(){
	String sql = "SELECT * FROM cliente";
	List<Cliente> lista = new ArrayList<>();
	Endereco end;
	EnderecoDAO endao = new EnderecoDAO();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		// Instancia um novo endereco e atribui o id atrelado ao cliente
		end = new Endereco();
		end.setId(rs.getLong(4));
		
		// Busca o endereco completo no banco
		end = endao.buscar(end);
		
		// Instancia um novo objeto cliente com o endereco associado
		Cliente cli = new Cliente(rs.getString(2), rs.getString(3), end);
		cli.setId(rs.getLong(1));
		
		// Adiciona o cliente na lista
		lista.add(cli);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return lista;
    }
}
