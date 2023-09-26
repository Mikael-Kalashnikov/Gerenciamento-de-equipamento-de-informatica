package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FrontController {
    
    @FXML private TextField login;
    @FXML private PasswordField senha;
    @FXML private DialogPane erroAutenticar;
    
    @FXML
    private void autenticar(ActionEvent event) {
	Usuario usu = new Usuario();
	usu.setLogin(login.getText());
	usu.setSenha(senha.getText());
	
	String loginUsu = usu.getLogin();
	String senhaUsu = usu.getSenha();
	
	if (loginUsu.equals("kanalense") && senhaUsu.equals("kanalense123") || loginUsu.equals("toinho") && senhaUsu.equals("toinho123")) {
	    // mostrar tela principal de responsavel
	}
	else if (loginUsu.equals("funcionario1") && senhaUsu.equals("func123")) {
	    // mostrar tela principal de funcionario
	}
	else {
	    // mostrar tela erro de autenticacao
	    DialogPane dp = new DialogPane();
	    dp.setContent(erroAutenticar.getContent());
	    Dialog dialog = new Dialog();
	    dialog.setDialogPane(dp);
	    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
	    dialog.showAndWait();
	}
    }
    
}
