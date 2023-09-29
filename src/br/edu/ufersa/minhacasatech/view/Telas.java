package br.edu.ufersa.minhacasatech.view;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
    private static Stage primaryStage;
    
    public static void main(String[] args) {
	launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
	setPrimaryStage(stage);
	stage.setTitle("Sistema MinhaCasaTech");
	telaLogin();
    }
    
    public static void setPrimaryStage(Stage primaryStage) {
	Telas.primaryStage = primaryStage;
    }
    
    public static Stage getPrimaryStage() {
	return Telas.primaryStage;
    }
    
    public static void telaLogin() throws IOException {
	Parent root = FXMLLoader.load(Telas.class.getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }
    
    public static void telaPrincipal() throws IOException {
	Parent root = FXMLLoader.load(Telas.class.getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaPrincipal.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }
    
    public static void telaEquipamentos() throws IOException {
	Parent root = FXMLLoader.load(Telas.class.getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaEquipamentos.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }
    
    public static void telaVendas() throws IOException {
	Parent root = FXMLLoader.load(Telas.class.getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaVendas.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }
    
    public static void telaLocais() throws IOException {
	Parent root = FXMLLoader.load(Telas.class.getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaLocais.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }
    
    public static void telaVendasFunc() throws IOException {
	Parent root = FXMLLoader.load(Telas.class.getResource("/br/edu/ufersa/minhacasatech/view/ve/TelaVendasFuncionario.fxml"));
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
    }
    
}
