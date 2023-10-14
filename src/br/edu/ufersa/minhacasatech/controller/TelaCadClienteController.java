package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaCadClienteController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    @FXML
    void cadastrarCliente() {
        
    }

    @FXML
    void logout() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml");
    }

    @FXML
    void telaClientes() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaClientes.fxml");
    }
    
}
