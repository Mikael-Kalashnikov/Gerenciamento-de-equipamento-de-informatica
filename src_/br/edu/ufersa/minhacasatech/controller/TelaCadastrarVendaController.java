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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class TelaCadastrarVendaController extends TelaPrincipalController implements Initializable {

    @FXML
    private ComboBox<String> cliente;
    
    @FXML
    private ComboBox<String> funcionario;
    
    @FXML
    private ListView<Equipamento> listView;
    
    @FXML
    private ChoiceBox<String> status;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EquipamentoBO eqbo = new EquipamentoBO();
        listView.getItems().setAll(eqbo.listar());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        status.getItems().setAll("Em Processamento", "Aprovada", "Cancelada");
        
        FuncionarioBO funcbo = new FuncionarioBO();
        funcionario.getItems().setAll(funcbo.listarNomesResponsavel());
        
        ClienteBO clibo = new ClienteBO();
        cliente.getItems().setAll(clibo.listarClientes());
    }
    
    @FXML
    private void cadastrarVenda() {
        try {
            ObservableList<Equipamento> selectedEquipamentos = listView.getSelectionModel().getSelectedItems();
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
            for (Equipamento equipamento : selectedEquipamentos) {
                    venda.setCliente(cli);
                    venda.setFuncionario(func);
                    venda.setEquipamento(equipamento);
                    venda.setStatus(status.getSelectionModel().getSelectedItem());
                    vbo.cadastrar(venda);
            }
            Dialog dialog = FrontController.callDialogPane("Message", "Venda cadastrada com sucesso!");
            dialog.showAndWait();
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaCadastrarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
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
