package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.ClienteBO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class TelaEditarClienteController extends TelaPrincipalController implements Initializable {

    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField nome;
    @FXML private TextField telefone;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cliente cli = TelaClienteController.getCliente();
        nome.setText(cli.getNome());
        cpf.setText(cli.getCpf());
        endereco.setText(cli.getEndereco());
        telefone.setText(cli.getTelefone());
    }
    
    @FXML
    private void editarCliente() {
        try {
            Cliente cli = TelaClienteController.getCliente();
            cli.setNome(nome.getText());
            cli.setCpf(cpf.getText());
            cli.setEndereco(endereco.getText());
            cli.setTelefone(telefone.getText());
            
            ClienteBO clibo = new ClienteBO();
            clibo.alterar(cli);
            Dialog success = FrontController.callDialogPane("Message", "Cliente editado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaEditarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
        }
    }
    
    @FXML
    private void close() {
        TelaClienteController.newStage.close();
        if (user.getIsResponsavel()) {
            telaClientes();
        } else {
            telaClientesFunc();
        }
    }
    
}
