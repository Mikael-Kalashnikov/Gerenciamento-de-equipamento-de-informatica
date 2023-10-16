package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaEditarFuncionarioController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField contato;
    @FXML private TextField login;
    @FXML private PasswordField senha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Funcionario func = TelaFuncionarioController.getFuncionario();
        nome.setText(func.getNome());
        cpf.setText(func.getCpf());
        endereco.setText(func.getEndereco());
        contato.setText(func.getTelefone());
    }
    
    @FXML
    private void editarFuncionario() {
        try {
            Funcionario func = TelaFuncionarioController.getFuncionario();
            func.setLogin(login.getText());
            func.setSenha(senha.getText());
            func.setNome(nome.getText());
            func.setCpf(cpf.getText());
            func.setEndereco(endereco.getText());
            func.setTelefone(contato.getText());
            func.setIsResponsavel(false);
            
            FuncionarioBO funcbo = new FuncionarioBO();
            funcbo.alterar(func);
            Dialog success = FrontController.callDialogPane("Message", "Funcionário editado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaEditarFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
    }
    
    @FXML
    private void close() {
        TelaFuncionarioController.newStage.close();
        telaFuncionarios();
    }
    
}
