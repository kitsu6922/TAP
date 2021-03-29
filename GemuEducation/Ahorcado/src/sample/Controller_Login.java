package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Controller_Login {
    public void acceder(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Juegos.fxml"));
            Scene scene=new Scene(root,1300,700);
            Main.stage.setScene(scene);
            Main.stage.centerOnScreen();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void cerrar(){
        System.exit(0);
    }
}
