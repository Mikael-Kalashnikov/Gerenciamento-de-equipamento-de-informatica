package br.edu.ufersa.minhacasatech.controller;

import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import br.edu.ufersa.minhacasatech.view.Telas;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FrontController {
    
    @FXML private TextField login;
    @FXML private PasswordField senha;
    @FXML private DialogPane erroAutenticar;
    
    public void autenticar(ActionEvent event) throws IOException {
	Usuario usu = new Usuario();
	usu.setLogin(login.getText());
	usu.setSenha(senha.getText());
	
	String loginUsu = usu.getLogin();
	String senhaUsu = usu.getSenha();
	
	if (loginUsu.equals("kanalense") && senhaUsu.equals("kanalense123") || loginUsu.equals("toinho") && senhaUsu.equals("toinho123")) {
	    // mostrar tela principal de responsavel
	    Telas.telaPrincipalResponsavel();
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
    
    public void telaEquipamentos() throws IOException {
	Telas.telaEquipamentos();
    }
    
}
