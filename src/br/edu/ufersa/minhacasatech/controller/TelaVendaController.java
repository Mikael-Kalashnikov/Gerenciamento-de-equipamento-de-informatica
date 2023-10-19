package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeEditarView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeExcluirView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.user;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.VendaBO;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import br.edu.ufersa.minhacasatech.view.Telas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javax.swing.JOptionPane;

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
    @FXML private TableColumn<Venda, String> equipamentoColumn;
    //@FXML private TableColumn<Venda, Integer> quantidadeColumn;
    //@FXML private TableColumn<Venda, Double> unitarioColumn;
    
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
        equipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("eqpsNome"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        
        //quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("qtdCompra"));
        //unitarioColumn.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        
        equipamentoColumn.setCellValueFactory(cellData -> {
            Venda venda = cellData.getValue();
            HashMap<String, Integer> eqpsNome = venda.getEquipamentosNome();
            StringBuilder equipamentos = new StringBuilder();
            for (Map.Entry<String, Integer> entry : eqpsNome.entrySet()) {
                equipamentos.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
            }
            return new SimpleStringProperty(equipamentos.toString());
        });
        
        tabelaVendas.setItems(vendas);
        filteredData = new FilteredList<>(tabelaVendas.getItems(), venda -> true);
        
        tabelaVendas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem aprovar = new MenuItem("Aprovar Venda");
                MenuItem cancelar = new MenuItem("Cancelar Venda");
                MenuItem editar = new MenuItem("Editar Venda");
                MenuItem excluir = new MenuItem("Excluir Venda");

                aprovar.setGraphic(iconeConfirmarView);
                cancelar.setGraphic(iconeCancelarView);
                editar.setGraphic(iconeEditarView);
                excluir.setGraphic(iconeExcluirView);

                aprovar.setOnAction(e -> {
                    selected = tabelaVendas.getSelectionModel().getSelectedItem();
                    aprovarVenda();
                });

                cancelar.setOnAction(e -> {
                    selected = tabelaVendas.getSelectionModel().getSelectedItem();
                    cancelarVenda();
                });

                editar.setOnAction(e -> {
                    selected = tabelaVendas.getSelectionModel().getSelectedItem();
                    telaEditarVenda();
                });

                excluir.setOnAction(e -> {
                    selected = tabelaVendas.getSelectionModel().getSelectedItem();
                    telaExcluirVenda(); 
                });

                contextMenu.getItems().addAll(aprovar, cancelar, editar, excluir);

                tabelaVendas.setContextMenu(contextMenu);
            }
        });
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
    
    public static Venda getVenda() {
        return selected;
    }
    
    private void aprovarVenda() {
        VendaBO vbo = new VendaBO();
        try {
            selected.setStatus("Aprovada");
            vbo.alterar(selected);
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog error = FrontController.callDialogPane("Message", ex.getMessage());
            error.showAndWait();
        }
        Dialog success = FrontController.callDialogPane("Message", "Venda aprovada");
        success.showAndWait();
        if (user.getIsResponsavel()) {
            telaVendas();
        } else {
            telaVendasFunc();
        }
    }
    
    private void cancelarVenda() {
        VendaBO vbo = new VendaBO();
        try {
            selected.setStatus("Cancelada");
            vbo.alterar(selected);
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dialog success = FrontController.callDialogPane("Message", "Venda cancelada");
        success.showAndWait();
        if (user.getIsResponsavel()) {
            telaVendas();
        } else {
            telaVendasFunc();
        }
    }
    
    @FXML
    private void telaExcluirVenda() {
        // gerar a tela de confirmacao de exclusao
        Dialog dialog = FrontController.callDialogPane("Dialog", "Tem certeza que deseja excluir essa venda?");
        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                excluirVenda();
            }
        });
    }
    
    private void excluirVenda() {
        VendaBO vbo = new VendaBO();
        vbo.remover(selected);
        Dialog success = FrontController.callDialogPane("Message", "Venda excluída");
        success.showAndWait();
        if (user.getIsResponsavel()) {
            telaVendas();
        } else {
            telaVendasFunc();
        }
    }
    
    @FXML
    private void gerarRelatorioVendas() {
        String timestamp = LocalDateTime.now().toString().replace('-', '_').replace('.', ' ').replace(':', '_');
                String nomeArquivo = "relatorio_vendas_" + timestamp + ".pdf";

                Document document = new Document();

                try {
                    PdfWriter.getInstance(document, new FileOutputStream(nomeArquivo));
                    document.open();
                    String reportName = "Relatório de Vendas";
                    Paragraph title = new Paragraph(reportName);
                    title.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(title);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dataAtual = new Date();
                    Paragraph dataRelatorio = new Paragraph("Data do Relatório: " + dateFormat.format(dataAtual));
                    document.add(dataRelatorio);

                    document.add(new Paragraph("------------------------------------------------"));

                    for (Venda venda : filteredData) {
                        document.add(new Paragraph("Cliente: " + venda.getCliente().getNome()));
                        
                        document.add(new Paragraph("Equipamento: " + venda.getEquipamentos().get(0).getNome()));
                        document.add(new Paragraph("Serial: " + venda.getEquipamentos().get(0).getSerial()));
                        document.add(new Paragraph("Valor unitário: R$" + venda.getEquipamentos().get(0).getValorUnitario()));
                        document.add(new Paragraph("Quantidade: " + venda.getEquipamentos().get(0).getVendidos()));
                        
                        document.add(new Paragraph("Total: R$" + venda.getValorTotal()));
                        document.add(new Paragraph("Data: " + venda.getDataVenda()));
                        document.add(new Paragraph("Status: " + venda.getStatus()));
                        document.add(new Paragraph("Funcionário: " + venda.getFuncionario().getNome()));
                        document.add(new Paragraph("------------------------------------------------"));
                    }

                    document.close();
                    JOptionPane.showMessageDialog(null, "Relatório de vendas gerado com sucesso!", "Relatório Gerado", JOptionPane.INFORMATION_MESSAGE);
                } catch (DocumentException | IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao gerar o relatório! VERIFIQUE OS DADOS" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @FXML
    private void telaCadastrarVenda() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarVenda.fxml");
    }
    
    @FXML
    private void telaEditarVenda() {
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarVenda.fxml");
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
