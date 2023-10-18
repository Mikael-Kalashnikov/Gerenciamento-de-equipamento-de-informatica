package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeEditarView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeExcluirView;
import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Local;
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

public class TelaLocalController extends TelaPrincipalController implements Initializable {
    
    @FXML private TableView<Local> tabelaLocais;
    @FXML private TableColumn<Local, Long> idColumn;
    @FXML private TableColumn<Local, String> nomeColumn;
    @FXML private TableColumn<Local, String> compartimentoColumn;
    @FXML private TableColumn<Local, String> dataColumn;
    
    private ObservableList<Local> locais;
    private FilteredList<Local> filteredData;
    private static Local selected;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalBO locbo = new LocalBO();
        locais = FXCollections.observableArrayList();
        locais.addAll(locbo.listar());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        compartimentoColumn.setCellValueFactory(new PropertyValueFactory<>("compartimento"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));

        tabelaLocais.setItems(locais);

        filteredData = new FilteredList<>(tabelaLocais.getItems(), local -> true);
        
        // verificar se selecionou alguma linha
        tabelaLocais.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem editar = new MenuItem("Editar Local");
                MenuItem excluir = new MenuItem("Excluir Local");
                
                editar.setGraphic(iconeEditarView);
                excluir.setGraphic(iconeExcluirView);
                
                editar.setOnAction(e -> {
                    selected = tabelaLocais.getSelectionModel().getSelectedItem();
                    telaEditarLocal();
                });
                
                excluir.setOnAction(e -> {
                    selected = tabelaLocais.getSelectionModel().getSelectedItem();
                    telaExcluirLocal();
                });
                
                contextMenu.getItems().addAll(editar, excluir);
                
                tabelaLocais.setContextMenu(contextMenu);
            }
        });
    }
    
    public static Local getLocal() {
        return selected;
    }
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarLocal;
    
    @FXML
    private void buscarLocal() {
        filteredData.setPredicate((Local local) -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarLocal.getText().toLowerCase();
            boolean atendeNome, atendeCompartimento, atendeDataInicio, atendeDataFim;
            // verifica se o usuario digitou a data inicial
            if (iniData == null || iniData.isEmpty())
                atendeDataInicio = true;
            else
                atendeDataInicio = local.getDataCadastro().compareTo(iniData) >= 0;
            //verifica se o usuario digitou a data final
            if (fimData == null || fimData.isEmpty())
                atendeDataFim = true;
            else
                atendeDataFim = local.getDataCadastro().compareTo(fimData) <= 0;
            // verifica se o usuario digitou algo no campo de pesquisa
            if (pesquisa == null || pesquisa.isEmpty()) {
                atendeNome = true;
                atendeCompartimento = true;
            }
            else {
                atendeNome = local.getNome().toLowerCase().contains(pesquisa);
                atendeCompartimento = local.getCompartimento().toLowerCase().contains(pesquisa);
            }
            
            // retorna somente os locais filtrados
            return atendeDataInicio && atendeDataFim && (atendeNome || atendeCompartimento);
        });
        
        // define os valores filtrados para a tabela
        tabelaLocais.setItems(filteredData);
    }
    
    @FXML
    private void telaCadastrarLocal() {
        // cria um novo pop-up para a tela de cadastro
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarLocal.fxml");
    }
    
    @FXML
    private void telaEditarLocal() {
        // cria um novo pop-up para a tela de editar
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarLocal.fxml");
    }
    
    @FXML
    private void telaExcluirLocal() {
        // gerar a tela de confirmacao de exclusao
        Dialog dialog = FrontController.callDialogPane("Dialog", "Tem certeza que deseja excluir o funcionário?");
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                excluirLocal();
            }
        });
    }
    
    @FXML
    private void excluirLocal() {
        LocalBO locbo = new LocalBO();
        locbo.remover(selected);
        Dialog success = FrontController.callDialogPane("Message", "Local excluído");
        success.showAndWait();
        telaLocais();
    }
    
}
