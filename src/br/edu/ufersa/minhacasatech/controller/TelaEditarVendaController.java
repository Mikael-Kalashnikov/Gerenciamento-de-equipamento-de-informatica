package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.user;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.ClienteBO;
import br.edu.ufersa.minhacasatech.model.bo.EquipamentoBO;
import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.bo.VendaBO;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
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

public class TelaEditarVendaController extends TelaPrincipalController implements Initializable {

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
    
    private final List<Equipamento> selectedEquipamentos = new ArrayList<>();
    private int total = 0;
    
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
                int i = 0;
                do {
                    Equipamento eq = listView.getSelectionModel().getSelectedItem();
                    eq.setQtdCompra(qtd);
                    eq.setVendidos(i);
                    total += eq.getValorUnitario();
                    selectedEquipamentos.add(eq);
                    i++;
                } while (i < qtd);
                for (Equipamento eq : selectedEquipamentos) {
                    System.out.println(eq);
                    System.out.print(eq.getQtdCompra());
                }
            } catch (InvalidInsertException ex) {
                ex.printStackTrace();
                Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
                dialog.showAndWait();
            }
        });
    }
    
    @FXML
    private void editarVenda() {
        try {
            VendaBO vbo = new VendaBO();
            vbo.alterar(TelaVendaController.getVenda());
            Dialog success = FrontController.callDialogPane("Message", "Venda editada com sucesso");
            success.showAndWait();
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaEditarVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog error = FrontController.callDialogPane("Error", ex.getMessage());
            error.showAndWait();
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
