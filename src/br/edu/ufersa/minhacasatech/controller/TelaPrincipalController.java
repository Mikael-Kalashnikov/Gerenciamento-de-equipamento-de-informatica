package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.view.Telas;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class TelaPrincipalController {
    
    static Funcionario user;
    static Stage newStage;
    static boolean init = true;
    
    @FXML
    void telaEquipamentos() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEquipamentos.fxml");
    }
    
    @FXML
    void telaVendas() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaVendas.fxml");
    }

    @FXML
    void telaLocais() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLocais.fxml");
    }

    @FXML
    void telaClientes() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaClientes.fxml");
    }

    @FXML
    void telaFuncionarios() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaFuncionarios.fxml");
    }
    
    @FXML
    void telaEquipamentosFunc() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaEquipamentosFuncionario.fxml");
    }
    
    @FXML
    void telaVendasFunc() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaVendasFuncionario.fxml");
    }
    
    @FXML
    void telaLocaisFunc() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLocaisFuncionario.fxml");
    }
    
    @FXML
    void telaClientesFunc() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaClientesFuncionario.fxml");
    }

    @FXML
    void logout() {
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml");
    }
    
}
