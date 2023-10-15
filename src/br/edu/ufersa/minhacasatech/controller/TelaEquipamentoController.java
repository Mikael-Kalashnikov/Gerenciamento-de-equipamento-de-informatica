package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Equipamento;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaEquipamentoController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarEquipamento;
    @FXML private TableView<Equipamento> tabelaEquipamentos;
    @FXML private TableColumn<Equipamento, String> serialColumn;
    @FXML private TableColumn<Equipamento, String> nomeColumn;
    @FXML private TableColumn<Equipamento, Double> precoColumn;
    @FXML private TableColumn<Equipamento, Integer> qtdColumn;
    @FXML private TableColumn<Equipamento, Local> localColumn;
    @FXML private TableColumn<Equipamento, Funcionario> responsavelColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    private void buscarEquipamento() {
        
    }

    @FXML
    private void cadastrarEquipamento() {
        
    }
    
    @FXML
    private void editarEquipamento() {
        
    }
    
    @FXML
    private void excluirEquipamento() {
        
    }
    
    @FXML
    private void gerarRelatorioEquipamentos() {
        
    }
    
    @FXML
    private void telaCadastrarEquipamento() {
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarEquipamento.fxml");
    }
    
    @FXML
    private void telaEditarEquipamento() {
        
    }
    
    @FXML
    private void telaExcluirEquipamento() {
        // gerar a tela de confirmacao de exclusao
    }
    
    @FXML
    private void close() {
        init = true;
        newStage.close();
        if (user.getIsResponsavel()) {
            telaEquipamentos();
        } else {
            telaEquipamentosFunc(); 
        }
    }
    
}
