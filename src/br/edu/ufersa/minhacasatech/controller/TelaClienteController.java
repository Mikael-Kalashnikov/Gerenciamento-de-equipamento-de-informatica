package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import br.edu.ufersa.minhacasatech.model.entity.Endereco;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaClienteController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField dataInicio;
    @FXML private TextField dataFim;
    @FXML private TextField pesquisarCliente;
    @FXML private TableView tabelaClientes;
    @FXML private TableColumn<Cliente, Long> idColumn;
    @FXML private TableColumn<Cliente, String> nomeColumn;
    @FXML private TableColumn<Cliente, String> cpfColumn;
    @FXML private TableColumn<Cliente, Endereco> enderecoColumn;
    @FXML private TableColumn<Cliente, String> contatoColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    private void buscarCliente() {
        
    }
    
    @FXML
    private void cadastrarCliente() {
        
    }
    
    @FXML
    private void editarCliente() {
        
    }
    
    @FXML
    private void excluirCliente() {
        
    }
    
    @FXML
    private void gerarRelatorioClientes() {
        
    }
    
    @FXML
    private void telaCadastrarCliente() {
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarCliente.fxml");
    }
    
    @FXML
    private void telaEditarCliente() {
        init = false;
        newStage = Telas.newScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEditarCliente.fxml");
    }
    
    @FXML
    private void telaExcluirCliente() {
        // gerar tela de confirmacao de exclusao
    }
    
    @FXML
    private void close() {
        init = true;
        newStage.close();
        if (user.getIsResponsavel()) {
            telaClientes();
        } else {
            telaClientesFunc();
        }
    }
    
}
