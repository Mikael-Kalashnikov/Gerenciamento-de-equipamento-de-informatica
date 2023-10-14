package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AlreadyExistsException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class TelaCadLocalController {
    
    @FXML private TextField nomeLocal;
    @FXML private TextField nomeCompartimento;
    
    @FXML
    private void cadastrarLocal() {
        try {
            Local local = new Local(nomeLocal.getText(), nomeCompartimento.getText());
            LocalBO locbo = new LocalBO();
            locbo.cadastrar(local);
            FrontController.callDialogPane("Message", "Local cadastrado com sucesso!");
        } catch (InvalidInsertException | AlreadyExistsException ex) {
            Logger.getLogger(TelaLocalController.class.getName()).log(Level.SEVERE, null, ex);
            
            // exibe tela de erro de local
            FrontController.callDialogPane("Error", ex.getMessage());
        }
    }
    
    @FXML
    private void telaLocais() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLocais.fxml");
    }
    
    @FXML
    private void logout() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml");
    }

}
