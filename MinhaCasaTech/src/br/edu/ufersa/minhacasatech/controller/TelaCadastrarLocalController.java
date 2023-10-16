package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class TelaCadastrarLocalController extends TelaPrincipalController {
    
    @FXML private TextField compartimento;
    @FXML private TextField nome;
    
    @FXML
    private void cadastrarLocal() {
        try {
            Local local = new Local(nome.getText(), compartimento.getText());
            LocalBO locbo = new LocalBO();
            local = locbo.cadastrar(local);
            Dialog success = FrontController.callDialogPane("Message", "Local cadastrado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaCadastrarLocalController.class.getName()).log(Level.SEVERE, null, ex);
            // exibe tela de erro de local
            Dialog dialog = FrontController.callDialogPane("Error", ex.getMessage());
            dialog.showAndWait();
        }
    }

    @FXML
    private void close() {
        TelaLocalController.newStage.close();
        if (user.getIsResponsavel()) {
            telaLocais();
        } else {
            telaLocaisFunc();
        }
    }
    
}
