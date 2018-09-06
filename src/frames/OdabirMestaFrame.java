/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;


import actions.IzborSedistaAction;
import actions.OtkaziMestaAction;
import controller.Connector;
import data.Sediste;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author nikol
 */
public class OdabirMestaFrame extends VBox {

    private GridPane gp = new GridPane();
    private Sediste[][] fields = new Sediste[10][8];
    private static ArrayList<String> rezervisane = new ArrayList<>();
    private HBox box = new HBox(50);
    private Button ok = new Button("Ok");
    private Button otkazi = new Button("Odustani");
    private String[] izabranaMesta;

    public OdabirMestaFrame() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {

                fields[i][j] = new Sediste("" + (i + 1));
                fields[i][j].setPrefHeight(30);
                fields[i][j].setPrefWidth(50);
                fields[i][j].setUserData(j + "," + i);
                fields[i][j].setOznaceno(false);
                fields[i][j].setStyle("-fx-background-color: white");
                gp.add(fields[i][j], i, j);
                final Sediste n = fields[i][j];
                fields[i][j].setOnAction((ActionEvent e) -> {
                    cekiraj(n);
                });

            }

        }
        this.setStyle("-fx-background-image: url(\"./images/Mini.png\");");
        provera();
        this.setPadding(new Insets(90, 10, 10, 5));
        this.getChildren().addAll(gp, box);
        box.setAlignment(Pos.BOTTOM_CENTER);
        this.setSpacing(10);
        box.getChildren().setAll(otkazi, ok);
        ok.setOnAction((event) -> {
            controller.Controller.getInstance().getAccountAFrame().getRedField().clear();
            controller.Controller.getInstance().getAccountAFrame().getSedisteField().clear();
            
            proveri();
            dodaj(izabranaMesta);
            IzborSedistaAction.getRezervacijaStage().close();
            controller.Controller.getInstance().getAccountAFrame().getRezervisiButton().setDisable(false);
        });
        otkazi.setOnAction(new OtkaziMestaAction());
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(7);
        gp.setHgap(3);
    }

    private void cekiraj(Sediste n) {
        String[] str = n.getUserData().toString().split(",");
        if (n.isOznaceno()) {
            n.setOznaceno(false);
            fields[Integer.parseInt(str[1])][Integer.parseInt(str[0])].setStyle("-fx-background-color: white");
        } else {
            n.setOznaceno(true);
            fields[Integer.parseInt(str[1])][Integer.parseInt(str[0])].setStyle("-fx-background-color: red");
        }

    }

    private void provera() {
      //  reset();
        Connector.getInstance().getAllRezervacije(controller.Controller.getInstance().getAccountAFrame().getIzabranaProjekcija().getId());
        rezervisane.forEach((t) -> {
            String[] pom = t.split(",");
            fields[Integer.parseInt(pom[1]) - 1][Integer.parseInt(pom[0]) - 1].setStyle("-fx-background-color: yellow");
            fields[Integer.parseInt(pom[1]) - 1][Integer.parseInt(pom[0]) - 1].setDisable(true);
        });
    }

    public static ArrayList<String> getRezervisane() {
        return rezervisane;
    }

    private void dodaj(String[] izabranaMesta) {
        for (int i = 0; i < izabranaMesta.length; i++) {
            String[] pom = izabranaMesta[i].split(",");
            controller.Controller.getInstance().getAccountAFrame().getRedField().setText(controller.Controller.getInstance().getAccountAFrame().getRedField().getText()+(Integer.parseInt(pom[0]) + 1) + ",");
            controller.Controller.getInstance().getAccountAFrame().getSedisteField().setText(controller.Controller.getInstance().getAccountAFrame().getSedisteField().getText()+(Integer.parseInt(pom[1]) + 1) + ",");
        }

    }

    private void proveri() {
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {

                if (fields[i][j].isOznaceno()) {
                    k++;

                }

            }

        }
        izabranaMesta = new String[k];
        int n = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {

                if (fields[i][j].isOznaceno()) {
                    izabranaMesta[n] = fields[i][j].getUserData().toString();
                    n++;
                }

            }

        }

    }
    public void reset(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {

             
                fields[i][j].setOznaceno(false);
                fields[i][j].setStyle("-fx-background-color: white");
              

            }

        }
    }

}
