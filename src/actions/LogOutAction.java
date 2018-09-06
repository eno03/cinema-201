/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Controller;
import frames.MainFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class LogOutAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getMainFrame() == null) {
            Controller.getInstance().setMainFrame(new MainFrame());
        }
        if (Controller.getInstance().getMainScene() == null) {
            Controller.getInstance().setMainScene(new Scene(Controller.getInstance().getMainFrame(), 660, 260));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Bioskop");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getMainScene());
 Controller.getInstance().getMainFrame().setStyle("-fx-background-image: url(\"./images/Cinema201.png\");");
        Controller.getInstance().setK(null);
    }

}
