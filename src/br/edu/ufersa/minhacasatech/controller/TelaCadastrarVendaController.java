package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.ClienteBO;
import br.edu.ufersa.minhacasatech.model.bo.EquipamentoBO;
import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.bo.VendaBO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;

public class TelaCadastrarVendaController extends TelaPrincipalController implements Initializable {

    @FXML
    private ComboBox<String> cliente;
    @FXML
    private ComboBox<String> funcionario;
    @FXML
    private ListView<Equipamento> listView;
    @FXML
    private ChoiceBox<String> status;
    @FXML
    private Button adicionar;
    @FXML
    private Spinner<Integer> quantidade;
    
    private static final List<Equipamento> selectedEquipamentos = new ArrayList<>();
    private double total = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EquipamentoBO eqbo = new EquipamentoBO();
        listView.getItems().setAll(eqbo.listar());
        listView.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Equipamento> change) -> {
            if (!change.getList().isEmpty()) {
                adicionar.setDisable(false);
                quantidade.setDisable(false);
            } else {
                adicionar.setDisable(true);
                quantidade.setDisable(false);
            }
        });
        
        status.getItems().setAll("Em Andamento", "Aprovada", "Cancelada");
        
        FuncionarioBO funcbo = new FuncionarioBO();
        funcionario.getItems().setAll(funcbo.listarNomesFuncionario());
        
        ClienteBO clibo = new ClienteBO();
        cliente.getItems().setAll(clibo.listarClientes());
        
        adicionar.setOnAction(e -> {
            try {
                int qtd = quantidade.getValue();
                Equipamento eq = listView.getSelectionModel().getSelectedItem();
                total += eq.getPreco() * qtd;
                eq.setQtdCompra(qtd);
                eq.setEstoque(eq.getEstoque() - qtd);
                selectedEquipamentos.add(eq);
            } catch (InvalidInsertException ex) {
                ex.printStackTrace();
                Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
                dialog.showAndWait();
            }
        });
    }
    
    @FXML
    private void cadastrarVenda() {
        try {
            if (cliente.getSelectionModel().getSelectedItem() == null) {
                throw new InvalidInsertException("Cliente inválido!");
            }
            if (funcionario.getSelectionModel().getSelectedItem() == null) {
                throw new InvalidInsertException("Funcionário inválido!");
            }
            if (status.getSelectionModel().getSelectedItem() == null) {
                throw new InvalidInsertException("Status inválido!");
            }
            
            Cliente cli = new Cliente();
            cli.setNome(cliente.getSelectionModel().getSelectedItem());
            ClienteBO clibo = new ClienteBO();
            cli = clibo.buscarPorNome(cli);
            
            FuncionarioBO funcbo = new FuncionarioBO();
            Funcionario func = new Funcionario();
            func.setNome(funcionario.getSelectionModel().getSelectedItem());
            func = funcbo.buscarPorNome(func);
            
            VendaBO vbo = new VendaBO();
            Venda venda = new Venda();
            venda.setCliente(cli);
            venda.setFuncionario(func);
            
            total = 0;
            List<Equipamento> eqs = new ArrayList<>();
            for (Equipamento eq : selectedEquipamentos) {
                eq.setQtdCompra(quantidade.getValue());
                int vendidos = eq.getVendidos() + quantidade.getValue();
                eq.setVendidos(vendidos);
                total += eq.getQtdCompra() * eq.getPreco();
                
                eqs.add(eq);
                
                EquipamentoBO atualizarEstoque = new EquipamentoBO();
                atualizarEstoque.alterar(eq);
            }
            venda.setEquipamentos(eqs);
            venda.setStatus(status.getSelectionModel().getSelectedItem());
            venda.setValorTotal(total);
            vbo.cadastrar(venda);            
            Dialog dialog = FrontController.callDialogPane("Message", "Venda cadastrada com sucesso!");
            dialog.showAndWait();
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaCadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
    }
    
    public static List<Equipamento> getEquipamentos() {
        return selectedEquipamentos;
    }
    
    @FXML
    private void close() {
        TelaVendaController.newStage.close();
        if (user.getIsResponsavel()) {
            telaVendas();
        } else {
            telaVendasFunc();
        }
    }
    
}
