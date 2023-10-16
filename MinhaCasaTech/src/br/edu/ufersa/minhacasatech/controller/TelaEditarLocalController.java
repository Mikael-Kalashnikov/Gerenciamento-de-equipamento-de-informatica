package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.LocalBO;
import br.edu.ufersa.minhacasatech.model.entity.Local;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class TelaEditarLocalController extends TelaPrincipalController implements Initializable {
    
    @FXML private TextField nome;
    @FXML private TextField compartimento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Local local = TelaLocalController.getLocal();
        nome.setText(local.getNome());
        compartimento.setText(local.getNomeCompartimento());
    }
    
    @FXML
    private void editarLocal() {
        try {
            Local local = TelaLocalController.getLocal();
            local.setNome(nome.getText());
            local.setNomeCompartimento(compartimento.getText());
            
            LocalBO locbo = new LocalBO();
            locbo.alterar(local);
            Dialog success = FrontController.callDialogPane("Message", "Local editado com sucesso!");
            success.showAndWait();
        } catch (InvalidInsertException ex) {
            Logger.getLogger(TelaEditarLocalController.class.getName()).log(Level.SEVERE, null, ex);
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
