package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import br.edu.ufersa.minhacasatech.model.entity.Responsavel;
import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class ResponsavelDAO extends BaseDAOImp<Responsavel> {
    
    @Override
    public Long inserir(Responsavel resp) {
	Connection con = BaseDAOImp.getConnection();
	String sql = "INSERT INTO responsavel (nome, login, senha, endereco, telefone) values (?, ?, ?, ?, ?)";
	Long id = null;
	try {
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setString(1, resp.getNome());
	    ps.setString(2, resp.getLogin());
	    ps.setString(3, resp.getSenha());
	    ps.setLong(4, resp.getEndereco().getId());
	    ps.setString(5, resp.getTelefone());
	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) {
		id = rs.getLong("id");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return id;
    }

    @Override
    public void deletar(Responsavel resp){
	String sql = "DELETE FROM responsavel WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, resp.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public void alterar(Responsavel resp){
	String sql = "UPDATE responsavel SET nome = ?, login = ?, senha = ?, endereco = ?, telefone = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, resp.getNome());
	    ps.setString(2, resp.getLogin());
	    ps.setString(3, resp.getSenha());
	    ps.setLong(4, resp.getEndereco().getId());
	    ps.setString(5, resp.getTelefone());
	    ps.setLong(6, resp.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public Responsavel buscarPorId(Responsavel resp) {
	String sql = "SELECT * FROM responsavel WHERE id = ?";
	Responsavel responsavel = null;
	Endereco end;
	EnderecoDAO endao;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, resp.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		end = new Endereco();
		end.setId(rs.getLong("endereco"));
		endao = new EnderecoDAO();
		end = endao.buscarPorId(end);
		
		responsavel = new Responsavel(rs.getString("login"), rs.getString("senha"));
		responsavel.setNome(rs.getString("nome"));
		responsavel.setEndereco(end);
		responsavel.setTelefone(rs.getString("telefone"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return responsavel;
    }
    
    @Override
    public List<Responsavel> listar(){
	String sql = "SELECT * FROM responsavel";
	List<Responsavel> lista = new ArrayList<>();
	Endereco end;
	EnderecoDAO endao;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next()) {
		end = new Endereco();
		endao = new EnderecoDAO();
		end.setId(rs.getLong("endereco"));
		end = endao.buscarPorId(end);
		
		Responsavel resp = new Responsavel(rs.getString("login"), rs.getString("senha"));
		resp.setNome(rs.getString("nome"));
		resp.setEndereco(end);
		resp.setTelefone(rs.getString("telefone"));
		
		lista.add(resp);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return lista;
    }
    
    public Usuario buscarPorLogin(Responsavel resp) {
        return null;
    }
}
