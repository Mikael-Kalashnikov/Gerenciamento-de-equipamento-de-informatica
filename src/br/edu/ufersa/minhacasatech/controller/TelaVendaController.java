package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeEditarView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.iconeExcluirView;
import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.user;
import br.edu.ufersa.minhacasatech.model.bo.VendaBO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import br.edu.ufersa.minhacasatech.view.Telas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
import javax.swing.JOptionPane;

public class TelaVendaController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TableView<Venda> tabelaVendas;
    @FXML private TableColumn<Venda, Long> idColumn;
    @FXML private TableColumn<Venda, Cliente> clienteColumn;
    @FXML private TableColumn<Venda, Equipamento> equipamentoColumn;
    @FXML private TableColumn<Venda, String> dataColumn;
    @FXML private TableColumn<Venda, String> statusColumn;
    @FXML private TableColumn<Venda, Funcionario> responsavelColumn;
    
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
        equipamentoColumn.setCellValueFactory(new PropertyValueFactory<>("equipamento"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        
        tabelaVendas.setItems(vendas);
        filteredData = new FilteredList<>(tabelaVendas.getItems(), venda -> true);
        
        // verificar se selecionou alguma linha se for responsavel
        if (user.getIsResponsavel()) { 
            tabelaVendas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem editar = new MenuItem("Editar Venda");
                    MenuItem excluir = new MenuItem("Excluir Venda");
                    
                    editar.setGraphic(iconeEditarView);
                    excluir.setGraphic(iconeExcluirView);
                    
                    editar.setOnAction(e -> {
                        selected = tabelaVendas.getSelectionModel().getSelectedItem();
                        telaEditarVenda();
                    });
                    
                    excluir.setOnAction(e -> {
                        selected = tabelaVendas.getSelectionModel().getSelectedItem();
                        telaExcluirVenda();
                    });
                    
                    contextMenu.getItems().addAll(editar, excluir);
                    
                    tabelaVendas.setContextMenu(contextMenu);
                }
            });
        }
    }
    
    @FXML
    private void buscarVenda() {
        
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
    private void gerarRelatorio() { 
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

            for (Venda venda : vendas) {
                document.add(new Paragraph("Cliente: " + venda.getCliente().getNome()));
                document.add(new Paragraph("Equipamento: " + venda.getEquipamento().getNome()));
                document.add(new Paragraph("Valor unitário: R$" + venda.getEquipamento().getPreco()));
                document.add(new Paragraph("Quantidade: " + venda.getEquipamento().getQuantidade()));
                document.add(new Paragraph("Total: R$" + venda.getEquipamento().getPreco() * venda.getEquipamento().getQuantidade()));
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