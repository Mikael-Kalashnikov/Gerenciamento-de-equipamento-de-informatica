package br.edu.ufersa.minhacasatech.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Telas {
    
    private static Stage stage;
    
    public Telas(Stage stage) {
        Telas.stage = stage;
        Telas.stage.setTitle("Sistema MinhaCasaTech");
	Image icon = new Image("/br/edu/ufersa/minhacasatech/view/ve/icon.png");
	Telas.stage.getIcons().add(icon);
    }
    
    public static void switchScene(String fxml) {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource(fxml));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
