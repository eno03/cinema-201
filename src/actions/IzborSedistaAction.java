/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import frames.OdabirMestaFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nikol
 */
public class IzborSedistaAction implements EventHandler<ActionEvent> {

    static Stage rezervacijaStage;

    public IzborSedistaAction() {
    }

    @Override
    public void handle(ActionEvent event) {
        rezervacijaStage = new Stage();
        OdabirMestaFrame mainFrame = new OdabirMestaFrame();
        mainFrame.setStyle("-fx-background-image: url(\"./images/book.png\");");
        Scene mainScene = new Scene(mainFrame, 360, 400);
        rezervacijaStage.setScene(mainScene);
        rezervacijaStage.show();
    }

    public static Stage getRezervacijaStage() {
        return rezervacijaStage;
    }

    public void setRezervacijaStage(Stage rezervacijaStage) {
        this.rezervacijaStage = rezervacijaStage;
    }

}
