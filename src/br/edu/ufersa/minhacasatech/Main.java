package br.edu.ufersa.minhacasatech;

import br.edu.ufersa.minhacasatech.view.Telas;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class Main extends Application  {
    
    public static void main(String[] args) {
	launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Telas telas = new Telas(primaryStage);
        Telas.switchScene("/br/edu/ufersa/minhacasatech/view/ve/TelaLogin.fxml");
    }
    
}
