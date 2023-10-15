package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.model.entity.Venda;
import br.edu.ufersa.minhacasatech.model.entity.Venda.StatusVenda;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaVendaController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TableView<Venda> tabelaVendas;
    @FXML private TableColumn<Venda, Long> idColumn;
    @FXML private TableColumn<Venda, Cliente> clienteColumn;
    @FXML private TableColumn<Venda, List<Equipamento>> equipamentoColumn;
    @FXML private TableColumn<Venda, Integer> qtdColumn;
    @FXML private TableColumn<Venda, Date> dataColumn;
    @FXML private TableColumn<Venda, StatusVenda> statusColumn;
    @FXML private TableColumn<Venda, Funcionario> responsavelColumn;
    @FXML private TableColumn<Venda, Double> totalColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
    private void gerarRelatorioVendas() {
        
    }
    
    @FXML
    private void telaCadastrarVenda() {
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarVenda.fxml");
    }
    
    @FXML
    private void telaEditarVenda() {
        
    }
    
    @FXML
    private void close() {
        init = true;
        newStage.close();
        if (user.getIsResponsavel()) {
            telaVendas();
        } else {
            telaVendasFunc();
        }
    }
    
}
