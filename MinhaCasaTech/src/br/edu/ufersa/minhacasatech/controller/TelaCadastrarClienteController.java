package br.edu.ufersa.minhacasatech.controller;

import static br.edu.ufersa.minhacasatech.controller.TelaPrincipalController.user;
import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.ClienteBO;
import br.edu.ufersa.minhacasatech.model.entity.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class TelaCadastrarClienteController extends TelaPrincipalController {
    
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField nome;
    @FXML private TextField telefone;
    
    @FXML
    private void cadastrarCliente() {
        try {
            Cliente cli = new Cliente(nome.getText(), cpf.getText(), telefone.getText(), endereco.getText());
            
            ClienteBO clibo = new ClienteBO();
            clibo.cadastrar(cli);
            Dialog success = FrontController.callDialogPane("Message", "Cliente cadastrado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog =  FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
    }
    
    @FXML
    private void gerarRelatorioClientes() {
        
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
