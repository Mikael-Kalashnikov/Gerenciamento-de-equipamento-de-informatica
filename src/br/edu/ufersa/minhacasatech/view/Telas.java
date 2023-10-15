package br.edu.ufersa.minhacasatech.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Telas {
    
    private static Stage stage;
    private static final Image icon = new Image("/br/edu/ufersa/minhacasatech/view/ve/icon.png");
    
    public Telas(Stage stage) {
        Telas.stage = stage;
        Telas.stage.setTitle("Sistema MinhaCasaTech");
        stage.setWidth(1366);
        stage.setHeight(728);
        stage.setMinWidth(840);
        stage.setMinHeight(520);
	Telas.stage.getIcons().add(icon);
    }
    
    public static void switchScene(String fxml) {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource(fxml));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Stage newScene(String fxml) {
        FXMLLoader loader = new FXMLLoader(Telas.class.getResource(fxml));
        Parent root;
        Stage newStage = null;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            newStage = new Stage();
            newStage.setTitle("Sistema MinhaCasaTech");
            newStage.setResizable(false);
            newStage.getIcons().add(icon);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newStage;
    }
    
}
