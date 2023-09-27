package br.edu.ufersa.minhacasatech.view;

import br.edu.ufersa.minhacasatech.model.entity.Responsavel;
import br.edu.ufersa.minhacasatech.model.entity.Usuario;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaPrincipalResp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaPrincipal.fxml"));
	Scene cena = new Scene(root);
	
        stage.setTitle("Sistema MinhaCasaTech");
        stage.setScene(cena);
        stage.show();
    }
    
    public static void main(String[] args) {
	Usuario kanalense = new Responsavel();
	kanalense.setLogin("kanalense");
	kanalense.setSenha("kanalense123");
	
	launch(args);
    }
    
}
