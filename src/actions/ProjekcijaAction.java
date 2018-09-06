/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import frames.ProjekcijaFrame;
import controller.Connector;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class ProjekcijaAction implements EventHandler<ActionEvent> {

    public ProjekcijaAction() {
    }

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getProjekcijaFrame() == null) {
            Controller.getInstance().setProjekcijaFrame(new ProjekcijaFrame());
        }
        if (Controller.getInstance().getProjekcijaScene() == null) {
            Controller.getInstance().setProjekcijaScene(new Scene(Controller.getInstance().getProjekcijaFrame(), 690, 320));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Projekcija");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getProjekcijaScene());
        Controller.getInstance().getProjekcijaFrame().setStyle("-fx-background-color: gainsboro");
        Connector.getInstance().getAllProjekcijas();
        Controller.getInstance().getProjekcijaFrame().getProjekcijaTableView().setItems(Connector.getInstance().getProjekcijas());
    }

}
