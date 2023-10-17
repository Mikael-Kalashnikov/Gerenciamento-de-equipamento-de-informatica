package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.bo.ClienteBO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.pdf.GerarPDFClientes;
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

public class TelaClienteController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarCliente;
    @FXML private TableView<Cliente> tabelaClientes;
    @FXML private TableColumn<Cliente, Long> idColumn;
    @FXML private TableColumn<Cliente, String> nomeColumn;
    @FXML private TableColumn<Cliente, String> cpfColumn;
    @FXML private TableColumn<Cliente, String> enderecoColumn;
    @FXML private TableColumn<Cliente, String> contatoColumn;
    @FXML private TableColumn<Cliente, String> dataColumn;
    
    private ObservableList<Cliente> clientes;
    private FilteredList<Cliente> filteredData;
    private static Cliente selected;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClienteBO clibo = new ClienteBO();
        clientes = FXCollections.observableArrayList();
        clientes.setAll(clibo.listar());
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        
        tabelaClientes.setItems(clientes);
        filteredData = new FilteredList<>(tabelaClientes.getItems(), cliente -> true);
        
        // verificar se selecionou alguma linha
        tabelaClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem editar = new MenuItem("Editar Cliente");
                MenuItem excluir = new MenuItem("Excluir Cliente");
                
                editar.setGraphic(iconeEditarView);
                excluir.setGraphic(iconeExcluirView);
                
                editar.setOnAction(e -> {
                    selected = tabelaClientes.getSelectionModel().getSelectedItem();
                    telaEditarCliente();
                });
                
                excluir.setOnAction(e -> {
                    selected = tabelaClientes.getSelectionModel().getSelectedItem();
                    telaExcluirCliente();
                });
                
                contextMenu.getItems().addAll(editar, excluir);
                
                tabelaClientes.setContextMenu(contextMenu);
            }
        });
    }
    
    public static Cliente getCliente() {
        return selected;
    }
    
    @FXML
    private void buscarCliente() {
        filteredData.setPredicate((Cliente cliente) -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarCliente.getText().toLowerCase();
            boolean atendeNome, atendeDataInicio, atendeDataFim;
            // verifica se o usuario digitou a data inicial
            if (iniData == null || iniData.isEmpty())
                atendeDataInicio = true;
            else
                atendeDataInicio = cliente.getDataCadastro().compareTo(iniData) >= 0;
            //verifica se o usuario digitou a data final
            if (fimData == null || fimData.isEmpty())
                atendeDataFim = true;
            else
                atendeDataFim = cliente.getDataCadastro().compareTo(fimData) <= 0;
            // verifica se o usuario digitou algo no campo de pesquisa
            if (pesquisa == null || pesquisa.isEmpty()) {
                atendeNome = true;
            }
            else {
                atendeNome = cliente.getNome().toLowerCase().contains(pesquisa);
            }
            
            // retorna somente os locais filtrados
            return atendeDataInicio && atendeDataFim && atendeNome;
        });
        
        // define os valores filtrados para a tabela
        tabelaClientes.setItems(filteredData);
    }
    
    @FXML
    private void gerarRelatorioClientes() {
        try {
        GerarPDFClientes gerador = new GerarPDFClientes();
        gerador.gerarRelatorio();
        // Exibir uma mensagem de sucesso
        Dialog success = FrontController.callDialogPane("Message", "Relatório de clientes gerado com sucesso.");
        success.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
        // Exibir uma mensagem de erro, se ocorrer um erro na geração do relatório
        Dialog error = FrontController.callDialogPane("Error", "Erro ao gerar o relatório de clientes.");
        error.showAndWait();
    }
    }
    
    @FXML
    private void telaCadastrarCliente() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarCliente.fxml");
    }
    
    @FXML
    private void telaEditarCliente() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarCliente.fxml");
    }
    
    @FXML
    private void telaExcluirCliente() {
        // gerar a tela de confirmacao de exclusao
        Dialog dialog = FrontController.callDialogPane("Dialog", "Tem certeza que deseja excluir esse cliente?");
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                excluirCliente();
            }
        });
    }
    
    @FXML
    private void excluirCliente() {
        ClienteBO clibo = new ClienteBO();
        clibo.remover(selected);
        Dialog success = FrontController.callDialogPane("Message", "Cliente excluído");
        success.showAndWait();
        telaClientes();
    }
    
}
