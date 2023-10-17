package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.FuncionarioBO;
import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastrarFuncionarioController extends TelaPrincipalController {
    
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField endereco;
    @FXML private TextField contato;
    @FXML private TextField login;
    @FXML private PasswordField senha;
    
    @FXML
    private void cadastrarFuncionario() throws InvalidInsertException {
        try {
            Funcionario func = new Funcionario(login.getText(), senha.getText());
            func.setNome(nome.getText());
            func.setCpf(cpf.getText());
            func.setEndereco(endereco.getText());
            func.setTelefone(contato.getText());
            
            FuncionarioBO funcbo = new FuncionarioBO();
            funcbo.cadastrar(func);
            Dialog success = FrontController.callDialogPane("Message", "Funcionário cadastrado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            Dialog dialog =  FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
    }
    
    @FXML
    private void close() {
        TelaFuncionarioController.newStage.close();
        telaFuncionarios();
    }
    
}
