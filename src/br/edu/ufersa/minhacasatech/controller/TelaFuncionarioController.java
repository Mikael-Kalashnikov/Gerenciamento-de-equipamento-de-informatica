package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaFuncionarioController extends TelaPrincipalController implements Initializable {

    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarFuncionario;
    @FXML private TableView<Funcionario> tabelaFuncionarios;
    @FXML private TableColumn<Funcionario, Long> idColumn;
    @FXML private TableColumn<Funcionario, String> nomeColumn;
    @FXML private TableColumn<Funcionario, String> cpfColumn;
    @FXML private TableColumn<Funcionario, Endereco> enderecoColumn;
    @FXML private TableColumn<Funcionario, String> contatoColumn;
    @FXML private TableColumn<Funcionario, Double> totalColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    private void buscarFuncionario() {
        
    }

    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField login;
    @FXML private PasswordField senha;
    
    @FXML
    private void cadastrarFuncionario() {
        FuncionarioBO funcbo = new FuncionarioBO();
        try {
            Funcionario func = new Funcionario(login.getText(), senha.getText());
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void editarFuncionario() {
        
    }
    
    @FXML
    private void excluirFuncionario() {
        
    }
    
    @FXML
    private void gerarRelatorioFuncionarios() {
        
    }
    
    @FXML
    private void telaCadastrarFuncionario() {
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarFuncionario.fxml");
    }
    
    @FXML
    private void telaEditarFuncionario() {
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarFuncionario.fxml");
    }
    
    @FXML
    private void telaExcluirFuncionario() {
        // gerar a tela de confirmacao de exclusao
    }
    
    @FXML
    private void close() {
        init = true;
        newStage.close();
        telaFuncionarios();
    }
    
}
