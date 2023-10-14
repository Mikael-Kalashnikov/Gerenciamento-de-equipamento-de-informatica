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

public class TelaClienteController implements Initializable {
    
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
    private void telaCadastrarCliente() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaCadastrarCliente.fxml");
    }
    
    @FXML
    private void telaEquipamentos() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEquipamentos.fxml");
    }

    @FXML
    private void telaVendas() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaVendas.fxml");
    }

    @FXML
    private void telaLocais() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLocais.fxml");
    }

    @FXML
    private void telaFuncionarios() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaFuncionarios.fxml");
    }

    @FXML
    private void logout() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml");
    }

}
