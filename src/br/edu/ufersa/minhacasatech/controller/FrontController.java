package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class FrontController {
    
    @FXML private TextField login;
    @FXML private TextField senha;
    @FXML private DialogPane erroAutenticar;
    public void autenticar(ActionEvent event) {
        if (login.getText().equals("Kanalense") && senha.getText().equals("kanalense123")) {
            // TODO mudar de cena
        }
        else {
	    // mostra a tela de erro
	    DialogPane dp = new DialogPane();
	    dp.setContent(erroAutenticar.getContent());
	    
	    Dialog dialog = new Dialog();
	    dialog.setDialogPane(dp);
	    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
	    dialog.show();
        }
    }
    
}
