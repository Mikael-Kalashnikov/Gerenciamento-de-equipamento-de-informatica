package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.view.Telas;
import javafx.fxml.FXML;

public class TelaPrincipalController {
    
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
    private void telaClientes() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaClientes.fxml");
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
