/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.RezervacijeFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class RezervacijeAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getRezervacijeFrame() == null) {
            Controller.getInstance().setRezervacijeFrame(new RezervacijeFrame());
        }
        if (Controller.getInstance().getRezervacijeScene() == null) {
            Controller.getInstance().setRezervacijeScene(new Scene(Controller.getInstance().getRezervacijeFrame(), 860, 320));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Rezervacije");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getRezervacijeScene());
        Controller.getInstance().getRezervacijeFrame().setStyle("-fx-background-color: gainsboro");
        Connector.getInstance().getAllRezervacije();
        Controller.getInstance().getRezervacijeFrame().getKarteTableView().setItems(Connector.getInstance().getRezervacijes());

    }

}
