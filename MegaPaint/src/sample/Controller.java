package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
    @FXML Canvas lienzo;
    @FXML ColorPicker cp_color;
    @FXML Slider slider,slider_d;
    @FXML Button bt_cuadrado,bt_circulo,bt_linea,bt_borrar,bt_llenar,bt_lapiz,bt_guardar;
    Color color=Color.WHITE;
    GraphicsContext context;
    @FXML ComboBox cb_opciones,cb_tamanio;

    @FXML public void initialize(){
        context = lienzo.getGraphicsContext2D();
        bt_cuadrado.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_cuadrado_b.png")).toString(),20,20,false,true)));
        bt_circulo.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_circulo_b.png")).toString(),20,20,false,true)));
        bt_linea.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_linea_b.png")).toString(),20,20,false,true)));
        bt_borrar.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_borrador_b.png")).toString(),20,20,false,true)));
        bt_llenar.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_llenar_b.png")).toString(),20,20,false,true)));
        bt_lapiz.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_brocha_b.png")).toString(),20,20,false,true)));
        bt_guardar.setGraphic(new ImageView(new Image((getClass().getResource("Iconos/icon_guardar_b.png")).toString(),20,20,false,true)));
        slider_d.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(cb_opciones.getSelectionModel().getSelectedIndex()>0){
                    cambio_opciones(newValue.intValue());
                }
            }
        });
        cb_opciones.getItems().addAll("Limpio","Chess","Cuadricula","Curvas","Estrella","Estrella Tapiz","Estrella Doble");
        cb_opciones.setValue("Limpio");
        cb_opciones.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    cambio_opciones((int) slider_d.getValue());
            }
        });
        cb_tamanio.getItems().addAll("Pequeño","Grande");
        cb_tamanio.setValue("Pequeño");
        cb_tamanio.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch (cb_tamanio.getSelectionModel().getSelectedIndex()){
                    case 0:
                        lienzo.setWidth(600);
                        cambio_opciones((int) slider_d.getValue());
                        break;
                    case 1:
                        lienzo.setWidth(900);
                        cambio_opciones((int) slider_d.getValue());
                        break;
                }
            }
        });

    }
    public void cambio_opciones(int v){
        context.setFill(Color.WHITE);
        context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
        double medida_x,medida_y;
        switch (cb_opciones.getSelectionModel().getSelectedIndex()){
            case 0:
                //limpio
                context.setFill(color);
                context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
                break;
            case 1:
                //chess
                medida_x=lienzo.getWidth()/v;
                medida_y=lienzo.getHeight()/v;
                boolean nb=true;
                boolean nb2=true;

                for (int i=0;i<v;i++){
                    for(int f=0;f<v;f++){
                        if(nb==true){
                            context.setFill(Color.BLACK);
                            nb=false;
                        }else{
                            context.setFill(Color.WHITE);
                            nb=true;
                        }
                        context.fillRect(medida_x*f,medida_y*i,(medida_x*f)+medida_x,(medida_y*i)+medida_y);
                    }
                    if(nb2==nb){
                        nb=!nb2;
                        nb2=nb;
                    }else{
                        nb2=nb;
                    }
                }
                break;
            case 2:
                //cuadricula
                medida_x=lienzo.getWidth()/v;
                medida_y=lienzo.getHeight()/v;
                for(int i=0;i<v;i++){
                    context.strokeLine(0,medida_y*i,lienzo.getWidth(),medida_y*i);
                    context.strokeLine(medida_x*i,0,medida_x*i,lienzo.getHeight());
                }
                break;
            case 3:
                //curvas
                medida_x=(lienzo.getWidth())/v;
                medida_y=(lienzo.getHeight())/v;
                for(int i=0;i<v;i++){
                    context.strokeLine(medida_x*(i+1),0,0,lienzo.getHeight()-(medida_y*i));
                    context.strokeLine(0,medida_y*(i),medida_x*(i+1),lienzo.getHeight());
                    context.strokeLine(medida_x*(i),0,lienzo.getWidth(),medida_y*(i+1));
                    context.strokeLine(lienzo.getWidth(),medida_y*i,lienzo.getWidth()-(medida_x*(i+1)),lienzo.getHeight());
                }
                break;
            case 4:
                //estrella
                context.strokeLine(lienzo.getWidth()/2,0,lienzo.getWidth()/2,lienzo.getHeight());
                context.strokeLine(0,lienzo.getHeight()/2,lienzo.getWidth(),lienzo.getHeight()/2);
                medida_x=(lienzo.getWidth()/2)/v;
                medida_y=(lienzo.getHeight()/2)/v;
                for(int i=0;i<v;i++){
                    context.strokeLine(lienzo.getWidth()/2,medida_y*i,(lienzo.getWidth()/2)+(medida_x*(i+1)),lienzo.getHeight()/2);
                    context.strokeLine(lienzo.getWidth()/2,medida_y*i,(lienzo.getWidth()/2)-(medida_x*(i+1)),lienzo.getHeight()/2);
                    context.strokeLine(lienzo.getWidth()/2,lienzo.getHeight()-(medida_y*i),(lienzo.getWidth()/2)+(medida_x*(i+1)),lienzo.getHeight()/2);
                    context.strokeLine(lienzo.getWidth()/2,lienzo.getHeight()-(medida_y*i),(lienzo.getWidth()/2)-(medida_x*(i+1)),lienzo.getHeight()/2);
                }
                break;
            case 5:
                //estrella tapiz
                medida_x=(lienzo.getWidth()/2)/v;
                medida_y=(lienzo.getHeight()/2)/v;
                for(int i=0;i<v;i++) {
                    //estrella recta
                    context.strokeLine(lienzo.getWidth() / 2, medida_y * i, (lienzo.getWidth() / 2) + (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    context.strokeLine(lienzo.getWidth() / 2, medida_y * i, (lienzo.getWidth() / 2) - (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    context.strokeLine(lienzo.getWidth() / 2, lienzo.getHeight() - (medida_y * i), (lienzo.getWidth() / 2) + (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    context.strokeLine(lienzo.getWidth() / 2, lienzo.getHeight() - (medida_y * i), (lienzo.getWidth() / 2) - (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    //exterior
                    context.strokeLine(medida_x*i,0,lienzo.getWidth()/2,medida_y*(i+1));
                    context.strokeLine(lienzo.getWidth()-(medida_x*i),0,lienzo.getWidth()/2,medida_y*(i+1));

                    context.strokeLine(0,medida_y*i,medida_x*(i+1),lienzo.getHeight()/2);
                    context.strokeLine(0,lienzo.getHeight()-(medida_y*i),medida_x*(i+1),lienzo.getHeight()/2);

                    context.strokeLine(medida_x*i,lienzo.getHeight(),lienzo.getWidth()/2,lienzo.getHeight()-(medida_y*(i+1)));
                    context.strokeLine(lienzo.getWidth()-(medida_x*i),lienzo.getHeight(),lienzo.getWidth()/2,lienzo.getHeight()-(medida_y*(i+1)));

                    context.strokeLine(lienzo.getWidth(),medida_y*i,lienzo.getWidth()-(medida_x*(i+1)),lienzo.getHeight()/2);
                    context.strokeLine(lienzo.getWidth(),lienzo.getHeight()-(medida_y*(i)),lienzo.getWidth()-(medida_x*(i+1)),lienzo.getHeight()/2);
                }
                break;
            case 6:
                //estrella doble
                medida_x=(lienzo.getWidth()/2)/v;
                medida_y=(lienzo.getHeight()/2)/v;
                for(int i=0;i<v;i++) {
                    //estrella inclinada
                    double medida_x2=((lienzo.getWidth()/8)*3)/v;
                    double medida_y2=((lienzo.getHeight()/8)*3)/v;
                    context.strokeLine(((lienzo.getWidth()/8)*1)+medida_x2*(i),((lienzo.getHeight()/8)*1)+medida_y2*(i),(lienzo.getWidth()/2)+(medida_x2*(i+1)),(lienzo.getHeight()/2)-(medida_y2*(i+1)));
                    context.strokeLine(((lienzo.getWidth()/8)*1)+medida_x2*(i),((lienzo.getHeight()/8)*1)+medida_y2*(i),(lienzo.getWidth()/2)-(medida_x2*(i+1)),(lienzo.getHeight()/2)+(medida_y2*(i+1)));
                    context.strokeLine(((lienzo.getWidth()/8)*1)+medida_x2*(i),((lienzo.getHeight()/8)*7)-(medida_y2*i),(lienzo.getWidth()/2)+(medida_x2*(i+1)),(lienzo.getHeight()/2)+(medida_y2*(i+1)));
                    context.strokeLine(((lienzo.getWidth()/8)*7)-(medida_x2*i),((lienzo.getHeight()/8)*7)-(medida_y2*i),(lienzo.getWidth()/2)+(medida_x2*(i+1)),(lienzo.getHeight()/2)-(medida_y2*(i+1)));
                    //estrella recta
                    context.strokeLine(lienzo.getWidth() / 2, medida_y * i, (lienzo.getWidth() / 2) + (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    context.strokeLine(lienzo.getWidth() / 2, medida_y * i, (lienzo.getWidth() / 2) - (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    context.strokeLine(lienzo.getWidth() / 2, lienzo.getHeight() - (medida_y * i), (lienzo.getWidth() / 2) + (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    context.strokeLine(lienzo.getWidth() / 2, lienzo.getHeight() - (medida_y * i), (lienzo.getWidth() / 2) - (medida_x * (i + 1)), lienzo.getHeight() / 2);
                    //circulo
                    context.strokeLine((lienzo.getWidth() / 2) - (medida_x * i), 0, 0, medida_y * (i + 1));
                    context.strokeLine((lienzo.getWidth() / 2) + (medida_x * i), 0, lienzo.getWidth(), medida_y * (i + 1));
                    context.strokeLine((lienzo.getWidth() / 2) - (medida_x * i), lienzo.getHeight(), 0, (lienzo.getHeight()) - (medida_y * (i + 1)));
                    context.strokeLine((lienzo.getWidth() / 2) + (medida_x * i), lienzo.getHeight(), lienzo.getWidth(), (lienzo.getHeight()) - (medida_y * (i + 1)));

                }
                break;
        }
    }
    public void pintar(){
        color=cp_color.getValue();
    }
    public void borrar(){
        color=Color.WHITESMOKE;
    }
    public void llenar(){
        context.setFill(color);
        context.fillRect(0,0,lienzo.getWidth(),lienzo.getHeight());
    }
    public void cambiar_color(ActionEvent evento){
        color=cp_color.getValue();
    }
    public void arrastrar(MouseEvent evento){
        context.setFill(color);
        context.fillOval(evento.getX(),evento.getY(),(int)slider.getValue(),(int)slider.getValue());
    }

}
