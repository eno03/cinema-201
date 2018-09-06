/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.AccountFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class OtkaziMestaAction implements EventHandler<ActionEvent> {

    public OtkaziMestaAction() {
    }

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getAccountAFrame() == null) {
            Controller.getInstance().setAccountAFrame(new AccountFrame());
        }
        if (Controller.getInstance().getAccountAScene() == null) {
            Controller.getInstance().setAccountAScene(new Scene(Controller.getInstance().getAccountAFrame(), 815, 640));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Bioskop");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getAccountAScene());
        Controller.getInstance().getAccountAFrame().setStyle("-fx-background-image: url(\"./images/Cinema201.png\");");

        Connector.getInstance().getAllMovies();
        Controller.getInstance().getAccountAFrame().getMovieTableView().setItems(Connector.getInstance().getMovies());
    }

}
