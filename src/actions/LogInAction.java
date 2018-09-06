/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Controller;
import frames.LogInFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class LogInAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getLoginFrame() == null) {
            Controller.getInstance().setLoginFrame(new LogInFrame());
        }
        if (Controller.getInstance().getLoginScene() == null) {
            Controller.getInstance().setLoginScene(new Scene(Controller.getInstance().getLoginFrame(), 365, 140));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Log In");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getLoginScene());
        Controller.getInstance().getLoginFrame().setStyle("-fx-background-image: url(\"./images/Mini.png\");");
    }

}
