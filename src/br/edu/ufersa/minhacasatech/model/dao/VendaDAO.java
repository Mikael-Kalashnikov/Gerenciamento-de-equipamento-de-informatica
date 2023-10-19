package br.edu.ufersa.minhacasatech.model.dao;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO extends BaseDAOImp<Venda> {
    
    @Override
    public Long inserir(Venda venda) {
	String sql = "INSERT INTO venda (id_cliente, id_funcionario, status, valor_total) values (?, ?, ?, ?)";
        Long id = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.setLong(2, venda.getFuncionario().getId());
            ps.setString(3, venda.getStatus());
            ps.setDouble(4, venda.getValorTotal());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong("id");
            }
            
            String sql_venda_equipamento = "INSERT INTO venda_equipamento (id_venda, id_equipamento, quantidade)"
                    + "VALUES (?, ?, ?)";
            List<Equipamento> equipamentos = venda.getEquipamentos();
            for (Equipamento eq : equipamentos) {
                con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement ps1 = con.prepareStatement(sql_venda_equipamento);
                ps1.setLong(1, id);
                ps1.setLong(2, eq.getId());
                ps1.setInt(3, eq.getQtdCompra());
                ps1.execute();
            }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
            BaseDAOImp.closeConnection();
        }
	return id;
    }
    
    @Override
    public void deletar(Venda venda) {
	String sql = "DELETE FROM venda WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getId());
	    ps.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
            BaseDAOImp.closeConnection();
        }
    }
    
    @Override
    public void alterar(Venda venda) {
	String sql = "UPDATE venda SET id_cliente = ?, id_funcionario = ?, status = ? WHERE id = ?";
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getCliente().getId());
	    ps.setLong(2, venda.getFuncionario().getId());
            ps.setString(3, venda.getStatus());
            ps.setLong(4, venda.getId());
            ps.execute();
            
            String sql_venda_equipamento = "UPDATE venda_equipamento SET id_equipamento = ?, quantidade = ? WHERE id_venda = ?";
            List<Equipamento> equipamentos = venda.getEquipamentos();
            for (Equipamento eq : equipamentos) {
                con = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement ps1 = con.prepareStatement(sql_venda_equipamento);
                ps1.setLong(1, eq.getId());
                ps1.setLong(2, eq.getQtdCompra());
                ps1.setLong(3, venda.getId());
                ps1.execute();
            }
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
            BaseDAOImp.closeConnection();
        }
    }
    
    @Override
    public Venda buscarPorId(Venda venda) {
	String sql = "SELECT * FROM vendas_view WHERE id = ?";
	Venda vendaEncontrada = null;
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setLong(1, venda.getId());
	    ps.execute();
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
                FuncionarioDAO funcdao = new FuncionarioDAO();
                Funcionario func = new Funcionario();
                func.setId(rs.getLong("funcionario"));
                func = funcdao.buscarPorId(func);
                
                ClienteDAO clidao = new ClienteDAO();
                Cliente cli = new Cliente();
                cli.setId(rs.getLong("cliente"));
                cli = clidao.buscarPorId(cli);
                
                List<Equipamento> listaEq = new ArrayList<>();
                HashMap<String, Integer> lista = new HashMap<>();
                EquipamentoDAO eqdao = new EquipamentoDAO();
                Equipamento eq = new Equipamento();
                eq.setId(rs.getLong("equipamento"));
                eq = eqdao.buscarPorId(eq);
                eq.setQtdCompra(rs.getInt("quantidade"));
                eq.setValorUnitario(rs.getDouble("valor_unitario"));
                lista.put(eq.getNome(), eq.getQtdCompra());
                listaEq.add(eq);
                
                vendaEncontrada = new Venda(cli, func);
                vendaEncontrada.setId(rs.getLong("id"));
                vendaEncontrada.setStatus(rs.getString("status"));
                vendaEncontrada.setValorTotal(rs.getDouble("valor_total"));
                vendaEncontrada.setDataVenda(rs.getDate("data_cadastro"));
                
                while (rs.next()) {
                    eq.setId(rs.getLong("equipamento"));
                    eq = eqdao.buscarPorId(eq);
                    eq.setQtdCompra(rs.getInt("quantidade"));
                    eq.setValorUnitario(rs.getDouble("valor_unitario"));
                    lista.put(eq.getNome(), eq.getQtdCompra());
                    listaEq.add(eq);
                }
                vendaEncontrada.setEquipamentosNome(lista);
                vendaEncontrada.setEquipamentos(listaEq);
	    }
	} catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return vendaEncontrada;
    }
    
    @Override
    public List<Venda> listar() {
	String sql = "SELECT * FROM vendas_view ORDER BY id";
	Map<Long, Venda> vendas = new HashMap<>();
        List<Venda> listaVendas = new ArrayList<>();
        List<Equipamento> equipamentos = new ArrayList<>();
	try {
	    Connection con = BaseDAOImp.getConnection();
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long vendaId = rs.getLong("id");
                
                if (!vendas.containsKey(vendaId)) {
                    FuncionarioDAO funcdao = new FuncionarioDAO();
                    Funcionario func = new Funcionario();
                    func.setId(rs.getLong("id_funcionario"));
                    func = funcdao.buscarPorId(func);
                    
                    ClienteDAO clidao = new ClienteDAO();
                    Cliente cli = new Cliente();
                    cli.setId(rs.getLong("id_cliente"));
                    cli = clidao.buscarPorId(cli);
                    
                    Venda vendaEncontrada = new Venda(cli, func);
                    vendaEncontrada.setId(vendaId);
                    vendaEncontrada.setStatus(rs.getString("status"));
                    vendaEncontrada.setValorTotal(rs.getDouble("valor_total"));
                    vendaEncontrada.setDataVenda(rs.getDate("data_cadastro"));
                    
                    HashMap<String, Integer> eqs = new HashMap<>();
                    vendaEncontrada.setEquipamentosNome(eqs);
                    
                    vendas.put(vendaId, vendaEncontrada);
                }
                
                EquipamentoDAO eqdao = new EquipamentoDAO();
                Equipamento eq = new Equipamento();
                eq.setId(rs.getLong("id_equipamento"));
                eq = eqdao.buscarPorId(eq);
                eq.setQtdCompra(rs.getInt("quantidade"));
                eq.setValorUnitario(rs.getDouble("valor_unitario"));
                equipamentos.add(eq);
                
                Venda vendaAtual = vendas.get(vendaId);
                vendaAtual.getEquipamentosNome().put(eq.getNome(), eq.getQtdCompra());
                vendaAtual.setEquipamentos(equipamentos);
            }
            listaVendas = new ArrayList<>(vendas.values());
	} catch (SQLException | InvalidInsertException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
            BaseDAOImp.closeConnection();
        }
	return listaVendas;
    }
    
}
