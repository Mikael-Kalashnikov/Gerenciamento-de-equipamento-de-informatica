package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeEditarView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeExcluirView;
import br.edu.ufersa.minhacasatech.model.bo.EquipamentoBO;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaEquipamentoController extends TelaPrincipalController implements Initializable {
    
    @FXML private TableView<Equipamento> tabelaEquipamentos;
    @FXML private TableColumn<Equipamento, String> nomeColumn;
    @FXML private TableColumn<Equipamento, String> serialColumn;
    @FXML private TableColumn<Equipamento, Double> precoColumn;
    @FXML private TableColumn<Equipamento, Integer> qtdColumn;
    @FXML private TableColumn<Equipamento, String> localColumn;
    @FXML private TableColumn<Equipamento, String> responsavelColumn;
    @FXML private TableColumn<Equipamento, String> dataColumn;
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarEquipamento;
    
    private ObservableList<Equipamento> equipamentos;
    private FilteredList<Equipamento> filteredData;
    private static Equipamento selected;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EquipamentoBO eqbo = new EquipamentoBO();
        equipamentos = FXCollections.observableArrayList();
        equipamentos.setAll(eqbo.listar());
        
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));
        precoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
        qtdColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        localColumn.setCellValueFactory(new PropertyValueFactory<>("local"));
        responsavelColumn.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        
        tabelaEquipamentos.setItems(equipamentos);
        filteredData = new FilteredList<>(tabelaEquipamentos.getItems(), equipamento -> true);
        
        // verificar se selecionou alguma linha se for responsavel
        if (user.getIsResponsavel()) { 
            tabelaEquipamentos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem editar = new MenuItem("Editar Equipamento");
                    MenuItem excluir = new MenuItem("Excluir Equipamento");
                    
                    editar.setGraphic(iconeEditarView);
                    excluir.setGraphic(iconeExcluirView);
                    
                    editar.setOnAction(e -> {
                        selected = tabelaEquipamentos.getSelectionModel().getSelectedItem();
                        telaEditarEquipamento();
                    });
                    
                    excluir.setOnAction(e -> {
                        selected = tabelaEquipamentos.getSelectionModel().getSelectedItem();
                        telaExcluirEquipamento();
                    });
                    
                    contextMenu.getItems().addAll(editar, excluir);
                    
                    tabelaEquipamentos.setContextMenu(contextMenu);
                }
            });
        }
    }
    
    public static Equipamento getEquipamento() {
        return selected;
    }
    
    @FXML
    private void buscarEquipamento() {
        filteredData.setPredicate((Equipamento funcionario) -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarEquipamento.getText().toLowerCase();
            boolean atendeNome, atendeDataInicio, atendeDataFim;
            // verifica se o usuario digitou a data inicial
            if (iniData == null || iniData.isEmpty())
                atendeDataInicio = true;
            else
                atendeDataInicio = funcionario.getDataCadastro().compareTo(iniData) >= 0;
            //verifica se o usuario digitou a data final
            if (fimData == null || fimData.isEmpty())
                atendeDataFim = true;
            else
                atendeDataFim = funcionario.getDataCadastro().compareTo(fimData) <= 0;
            // verifica se o usuario digitou algo no campo de pesquisa
            if (pesquisa == null || pesquisa.isEmpty()) {
                atendeNome = true;
            }
            else {
                atendeNome = funcionario.getNome().toLowerCase().contains(pesquisa);
            }
            
            // retorna somente os locais filtrados
            return atendeDataInicio && atendeDataFim && atendeNome;
        });
        
        // define os valores filtrados para a tabela
        tabelaEquipamentos.setItems(filteredData);
    }
    
    @FXML
    private void excluirEquipamento() {
        EquipamentoBO eqbo = new EquipamentoBO();
        eqbo.remover(selected);
        Dialog success = FrontController.callDialogPane("Message", "Equipamento excluído");
        success.showAndWait();
        telaEquipamentos();
    }
    
    @FXML
    private void telaCadastrarEquipamento() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarEquipamento.fxml");
    }
    
    @FXML
    private void telaEditarEquipamento() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarEquipamento.fxml");
    }
    
    @FXML
    private void telaExcluirEquipamento() {
        // gerar a tela de confirmacao de exclusao
        Dialog dialog = FrontController.callDialogPane("Dialog", "Tem certeza que deseja excluir esse equipamento?");
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                excluirEquipamento();
            }
        });
    }
    
}
