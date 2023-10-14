package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.exception.AutenticationException;
import br.edu.ufersa.minhacasatech.exception.InvalidInsertException;
import br.edu.ufersa.minhacasatech.model.bo.UsuarioBO;
import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class FrontController implements Initializable {
    
    static Label labelError;
    static Label labelMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // label de mensagem
        labelMessage = new Label();
        labelMessage.setFont(Font.font("Consolas", 15));
        
        // label de mensagem de erro
        labelError = new Label();
        labelError.setTextFill(Paint.valueOf("#ff0606"));
        labelError.setFont(Font.font("Consolas", 15));
    }
    
    // cria um painel de dialogo personalizado
    public static void callDialogPane(String typeMessage, String message) {
        DialogPane dp = new DialogPane();
        if (typeMessage.equals("Error")) {
            labelError.setText(message);
            dp.setContent(labelError);
        }
        else {
            labelMessage.setText(message);
            dp.setContent(labelMessage);
        }
        Dialog dialog = new Dialog();
        dialog.setDialogPane(dp);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }
    
    @FXML private TextField login;
    @FXML private PasswordField senha;
    
    @FXML
    public void autenticar(ActionEvent event) {
        Usuario usu = new Usuario();
        try {
            // pega os dados digitados na tela de login
            usu.setLogin(login.getText());
            usu.setSenha(senha.getText());
            
            UsuarioBO usubo = new UsuarioBO();
            usu = usubo.autenticar(usu);
            if (usu.getTipo().equals("Responsavel")) {
                Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaPrincipal.fxml");
            }
            else {
                Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaVendasFuncionario.fxml");
            }
        } catch (InvalidInsertException | AutenticationException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            
            // mostrar tela erro de autenticacao
            callDialogPane("Error", ex.getMessage());
        }
    }
    
}
