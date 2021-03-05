package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.Locale;
import java.util.Random;

public class Controller {
    @FXML HBox letras_contenedor;
    @FXML AnchorPane principal;
    int correcto=0;
    int incorrecto=0;
    @FXML public void initialize(){
        Random random=new Random();
        String palabras[]={"TELEFONO","AURICULARES","COMPUTADORA","CONTROL","CARRO","MONITOR","MUSICA"};
        String palabra=palabras[random.nextInt(6)];
        System.out.println(palabra);
        int ayuda=random.nextInt(palabra.length()-1);
        System.out.println(""+ayuda);
        int letra_ayuda[]=new int[ayuda];
        for(int i=0;i<ayuda;i++){
            boolean pase=true;
            int letra_ayudax=0;
            System.out.println("for"+ i);
            while(pase==true){
                System.out.println("dentro");
                letra_ayudax=random.nextInt(palabra.length()-1);
                if(i==0){
                    pase=false;
                    letra_ayuda[i]=letra_ayudax;
                    System.out.println("bien0");
                }else{
                    pase=false;
                    for(int f=0;f<i;f++){
                        if(letra_ayuda[f]==letra_ayudax){
                            pase=true;
                            System.out.println("repetidas");
                        }
                    }
                    if(pase==false){
                        pase=false;
                        letra_ayuda[i]=letra_ayudax;
                        System.out.println("bien");
                    }

                }

            }
            System.out.println(letra_ayudax+"letra ayudax");
        }
        TextField [] textos = new TextField[palabra.length()];
        for(int i=0;i<palabra.length();i++) {
            textos[i]=new TextField();
            textos[i].setPrefWidth(50);
            textos[i].setPrefHeight(50);
            for(int x=0;x<letra_ayuda.length;x++){
                if(letra_ayuda[x]==i){
                    textos[i].setText(palabra.substring(letra_ayuda[x],letra_ayuda[x]+1));
                    textos[i].setDisable(true);
                    correcto++;
                    System.out.println(correcto+"");
                }
            }
            textos[i].setId("t-"+i);
            textos[i].setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    TextField texto=(TextField) event.getTarget();
                    String [] id=texto.getId().split("-");
                    if(palabra.charAt(Integer.parseInt(id[1]))==texto.getText().toUpperCase().charAt(0)){
                        texto.setDisable(true);
                        texto.setText(texto.getText().toUpperCase());
                        correcto++;
                        System.out.println(correcto+"");
                        System.out.println(palabra.length()+"");
                        if(correcto==palabra.length()){
                            Alert alerta=new Alert(Alert.AlertType.INFORMATION,"FELICIDADES GANASTE");
                            alerta.show();
                        }
                    }else{
                        texto.setText("");
                        incorrecto++;
                        pintar();
                        if(incorrecto>=6){
                            Alert alerta=new Alert(Alert.AlertType.ERROR,"LO SIENTO, PERDISTE");
                            alerta.show();
                            for(int fi=0;fi<textos.length;fi++){
                                textos[fi].setDisable(true);
                            }
                        }
                    }
                }
            });
            letras_contenedor.getChildren().add(textos[i]);
        }
    }

    public void pintar(){
        ImageView cabeza=new ImageView(new Image("sample/img/cabeza.png"));
        ImageView cuerpo=new ImageView(new Image("sample/img/cuerpo.png"));
        ImageView piernad=new ImageView(new Image("sample/img/piernad.png"));
        ImageView piernai=new ImageView(new Image("sample/img/piernai.png"));
        ImageView brazod=new ImageView(new Image("sample/img/brazod.png"));
        ImageView brazoi=new ImageView(new Image("sample/img/brazoi.png"));
        for(int i=1;i<=incorrecto;i++){
            switch (i){
                case 1:
                    cabeza.setFitHeight(100);
                    cabeza.setFitWidth(100);
                    cabeza.setLayoutX(200);
                    cabeza.setLayoutY(100);
                    principal.getChildren().add(cabeza);
                    break;
                case 2:
                    cuerpo.setFitHeight(200);
                    cuerpo.setFitWidth(100);
                    cuerpo.setLayoutX(200);
                    cuerpo.setLayoutY(200);
                    principal.getChildren().add(cuerpo);
                    break;
                case 3:
                    brazoi.setFitHeight(100);
                    brazoi.setFitWidth(45);
                    brazoi.setLayoutX(155);
                    brazoi.setLayoutY(200);
                    principal.getChildren().add(brazoi);
                    break;
                case 4:
                    brazod.setFitHeight(100);
                    brazod.setFitWidth(45);
                    brazod.setLayoutX(300);
                    brazod.setLayoutY(200);
                    principal.getChildren().add(brazod);
                    break;
                case 5:
                    piernad.setFitHeight(100);
                    piernad.setFitWidth(45);
                    piernad.setLayoutX(255);
                    piernad.setLayoutY(390);
                    principal.getChildren().add(piernad);
                    break;
                case 6:
                    piernai.setFitHeight(100);
                    piernai.setFitWidth(49);
                    piernai.setLayoutX(200);
                    piernai.setLayoutY(390);
                    principal.getChildren().add(piernai);
                    break;
            }
        }
    }
}
