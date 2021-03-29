package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class Controller_Juegos {
    @FXML AnchorPane panel_ahorcado,panel_ajustes,panel_ahorcado_bd,panel_inicio;
    @FXML JFXTextField tf_ahorcadobd_agcategoria;
    @FXML VBox vbox_categorias_ahorcadobd,vbox_categorias_ahorcado;

    @FXML public void initialize(){
        panel_inicio.setVisible(true);
        panel_ajustes.setVisible(false);
        panel_ahorcado.setVisible(false);
        panel_ahorcado_bd.setVisible(false);
    }

    public void cambio_panel_inicio(){
        panel_inicio.setVisible(true);
        panel_ajustes.setVisible(false);
        panel_ahorcado.setVisible(false);
        panel_ahorcado_bd.setVisible(false);
    }

    public void cambio_panel_ahorcado(){
        panel_inicio.setVisible(false);
        panel_ajustes.setVisible(false);
        panel_ahorcado.setVisible(true);
        panel_ahorcado_bd.setVisible(false);
    }

    public void cambio_panel_ajustes(){
        panel_inicio.setVisible(false);
        panel_ajustes.setVisible(true);
        panel_ahorcado.setVisible(false);
        panel_ahorcado_bd.setVisible(false);
    }

    public void cerrar(){
        System.exit(0);
    }

    public void  cambio_panel_ahorcado_ajustes(){
        panel_inicio.setVisible(false);
        panel_ajustes.setVisible(false);
        panel_ahorcado.setVisible(false);
        panel_ahorcado_bd.setVisible(true);
    }

    public void cerrar_sesion(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene=new Scene(root,630,380);
            Main.stage.setScene(scene);
            Main.stage.centerOnScreen();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void  agregar_categoria(){

        JFXButton boton=new JFXButton(tf_ahorcadobd_agcategoria.getText());
        boton.setPrefWidth(160);
        boton.setPrefHeight(60);
        boton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Aqui se revisara la base de datos de la categoria agregada");
                alert.show();
            }
        });
        boton.setStyle("-fx-background-color:#424238;-fx-text-fill:#ffffff;-fx-background-radius:30;");
        vbox_categorias_ahorcadobd.getChildren().addAll(boton);
        JFXButton boton2=new JFXButton(tf_ahorcadobd_agcategoria.getText());
        boton2.setPrefWidth(160);
        boton2.setPrefHeight(60);
        boton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert=new Alert(Alert.AlertType.INFORMATION,"Aqui se revisara la base de datos de la categoria agregada");
                alert.show();
            }
        });
        boton2.setStyle("-fx-background-color:#424238;-fx-text-fill:#ffffff;-fx-background-radius:30;");
        vbox_categorias_ahorcado.getChildren().addAll(boton2);
    }
}
