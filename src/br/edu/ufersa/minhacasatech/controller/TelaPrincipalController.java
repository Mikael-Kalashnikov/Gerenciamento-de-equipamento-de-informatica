package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Funcionario;
import br.edu.ufersa.minhacasatech.view.Telas;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TelaPrincipalController {
    
    static final Image iconeEditar = new Image(TelaPrincipalController.class.getResourceAsStream("/br/edu/ufersa/minhacasatech/view/ve/icons8-edit-48 56.png"));
    static final Image iconeExcluir = new Image(TelaPrincipalController.class.getResourceAsStream("/br/edu/ufersa/minhacasatech/view/ve/icons8-delete-30 61.png"));
    static final Image iconeConfirmar = new Image(TelaPrincipalController.class.getResourceAsStream("/br/edu/ufersa/minhacasatech/view/ve/icons8-confirm-48.png"));
    static final Image iconeCancelar = new Image(TelaPrincipalController.class.getResourceAsStream("/br/edu/ufersa/minhacasatech/view/ve/icons8-cancel-32.png"));
    static final ImageView iconeEditarView = new ImageView(iconeEditar);
    static final ImageView iconeExcluirView = new ImageView(iconeExcluir);
    static final ImageView iconeConfirmarView = new ImageView(iconeConfirmar);
    static final ImageView iconeCancelarView = new ImageView(iconeCancelar);
    
    public TelaPrincipalController() {
        iconeEditarView.setFitWidth(25);
        iconeEditarView.setFitHeight(25);
        iconeExcluirView.setFitWidth(25);
        iconeExcluirView.setFitHeight(25);
        iconeConfirmarView.setFitWidth(25);
        iconeConfirmarView.setFitHeight(25);
        iconeCancelarView.setFitWidth(25);
        iconeCancelarView.setFitHeight(25);
    }
    
    static Funcionario user;
    static Stage newStage;
    
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
