package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeEditarView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeExcluirView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.user;
import br.edu.ufersa.minhacasatech.model.bo.VendaBO;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaVendaController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarVenda;
    @FXML private TableView<Venda> tabelaVendas;
    @FXML private TableColumn<Venda, Long> idColumn;
    @FXML private TableColumn<Venda, String> clienteColumn;
    @FXML private TableColumn<Venda, Funcionario> responsavelColumn;
    @FXML private TableColumn<Venda, String> statusColumn;
    @FXML private TableColumn<Venda, Double> totalColumn;
    @FXML private TableColumn<Venda, String> dataColumn;
    @FXML private TableColumn<Venda, Equipamento> equipamentoColumn;
    @FXML private TableColumn<Equipamento, Integer> quantidadeColumn;
    @FXML private TableColumn<Equipamento, Double> unitarioColumn;
    
    private ObservableList<Venda> vendas;
    private FilteredList<Venda> filteredData;
    private static Venda selected;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VendaBO eqbo = new VendaBO();
        vendas = FXCollections.observableArrayList();
        vendas.setAll(eqbo.listar());
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        responsavelColumn.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        equipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("equipamentos"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("qtdCompra"));
        unitarioColumn.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        
        tabelaVendas.setItems(vendas);
        filteredData = new FilteredList<>(tabelaVendas.getItems(), venda -> true);
        
        // verificar se selecionou alguma linha se for responsavel
        if (user.getIsResponsavel()) { 
            tabelaVendas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem detalhar = new MenuItem("Detalhar Venda");
                    MenuItem editar = new MenuItem("Editar Venda");
                    MenuItem aprovar = new MenuItem("Aprovar Venda");
                    MenuItem cancelar = new MenuItem("Cancelar Venda");
                    
                    editar.setGraphic(iconeEditarView);
                    cancelar.setGraphic(iconeExcluirView);
                    
                    editar.setOnAction(e -> {
                        selected = tabelaVendas.getSelectionModel().getSelectedItem();
                        telaEditarVenda();
                    });
                    
                    cancelar.setOnAction(e -> {
                        selected = tabelaVendas.getSelectionModel().getSelectedItem();
                        telaExcluirVenda();
                    });
                    
                    contextMenu.getItems().addAll(editar, cancelar);
                    
                    tabelaVendas.setContextMenu(contextMenu);
                }
            });
        }
    }
    
    @FXML
    private void buscarVenda() {
        filteredData.setPredicate((Venda venda) -> {
            String iniData = dataInicio.getText();
            String fimData = dataFim.getText();
            String pesquisa = pesquisarVenda.getText().toLowerCase();
            boolean atendeCliente, atendeStatus, atendeDataInicio, atendeDataFim;
            // verifica se o usuario digitou a data inicial
            if (iniData == null || iniData.isEmpty())
                atendeDataInicio = true;
            else
                atendeDataInicio = venda.getDataVenda().compareTo(iniData) >= 0;
            //verifica se o usuario digitou a data final
            if (fimData == null || fimData.isEmpty())
                atendeDataFim = true;
            else
                atendeDataFim = venda.getDataVenda().compareTo(fimData) <= 0;
            // verifica se o usuario digitou algo no campo de pesquisa
            if (pesquisa == null || pesquisa.isEmpty()) {
                atendeCliente = true;
                atendeStatus = true;
            }
            else {
                atendeCliente = venda.getCliente().getNome().toLowerCase().contains(pesquisa);
                atendeStatus = venda.getStatus().toLowerCase().contains(pesquisa);
            }
            
            // retorna somente os locais filtrados
            return atendeDataInicio && atendeDataFim && (atendeCliente || atendeStatus);
        });
        
        // define os valores filtrados para a tabela
        tabelaVendas.setItems(filteredData);
    }
    
    @FXML
    private void cadastrarVenda() {
        
    }
    
    @FXML
    private void editarVenda() {
        
    }
    
    @FXML
    private void telaExcluirVenda() {
        
    }
    
    @FXML
    private void telaCancelarVenda() {
        
    }
    
    @FXML
    private void gerarRelatorioVendas() {
        
    }
    
    @FXML
    private void telaCadastrarVenda() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarVenda.fxml");
    }
    
    @FXML
    private void telaEditarVenda() {
        
    }
    
    @FXML
    private void close() {
        newStage.close();
        if (user.getIsResponsavel()) {
            telaVendas();
        } else {
            telaVendasFunc();
        }
    }
    
}
