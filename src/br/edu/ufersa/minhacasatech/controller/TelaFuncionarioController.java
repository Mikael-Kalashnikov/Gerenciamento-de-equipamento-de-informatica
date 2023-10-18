package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaFuncionarioController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarFuncionario;
    @FXML private TableView<Funcionario> tabelaFuncionarios;
    @FXML private TableColumn<Funcionario, Long> idColumn;
    @FXML private TableColumn<Funcionario, String> nomeColumn;
    @FXML private TableColumn<Funcionario, String> cpfColumn;
    @FXML private TableColumn<Funcionario, String> enderecoColumn;
    @FXML private TableColumn<Funcionario, String> contatoColumn;
    @FXML private TableColumn<Funcionario, Double> totalColumn;
    @FXML private TableColumn<Funcionario, String> dataColumn;
    
    private ObservableList<Funcionario> funcionarios;
    private FilteredList<Funcionario> filteredData;
    private static Funcionario selected;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FuncionarioBO funcbo = new FuncionarioBO();
        funcionarios = FXCollections.observableArrayList();
        funcionarios.setAll(funcbo.listar());
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalVendas"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        
        tabelaFuncionarios.setItems(funcionarios);
        filteredData = new FilteredList<>(tabelaFuncionarios.getItems(), funcionario -> true);
        
        // verificar se selecionou alguma linha
        tabelaFuncionarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem editar = new MenuItem("Editar Funcionário");
                MenuItem excluir = new MenuItem("Excluir Funcionário");
                
                editar.setGraphic(iconeEditarView);
                excluir.setGraphic(iconeExcluirView);
                
                editar.setOnAction(e -> {
                    selected = tabelaFuncionarios.getSelectionModel().getSelectedItem();
                    telaEditarFuncionario();
                });
                
                excluir.setOnAction(e -> {
                    selected = tabelaFuncionarios.getSelectionModel().getSelectedItem();
                    telaExcluirFuncionario();
                });
                
                contextMenu.getItems().addAll(editar, excluir);
                
                tabelaFuncionarios.setContextMenu(contextMenu);
            }
        });
    }
    
    @FXML
    private void buscarFuncionario() {
        filteredData.setPredicate((Funcionario funcionario) -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarFuncionario.getText().toLowerCase();
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
        tabelaFuncionarios.setItems(filteredData);
    }
    
    @FXML
    private void gerarRelatorioFuncionarios() {
        // TODO
    }
    
    public static Funcionario getFuncionario() {
        return selected;
    }
    
    @FXML
    private void telaCadastrarFuncionario() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarFuncionario.fxml");
    }
    
    @FXML
    private void telaEditarFuncionario() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarFuncionario.fxml");
    }
    
    @FXML
    private void telaExcluirFuncionario() {
        // gerar a tela de confirmacao de exclusao
        Dialog dialog = FrontController.callDialogPane("Dialog", "Tem certeza que deseja excluir o funcionário?");
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                excluirFuncionario();
            }
        });
    }
    
    @FXML
    private void excluirFuncionario() {
        FuncionarioBO funcbo = new FuncionarioBO();
        funcbo.remover(selected);
        Dialog success = FrontController.callDialogPane("Message", "Funcionário excluído");
        success.showAndWait();
        telaFuncionarios();
    }
    
}
