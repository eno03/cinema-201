/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Controller;
import frames.SignUpFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class SignUpAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getSignUpFrame() == null) {
            Controller.getInstance().setSignUpFrame(new SignUpFrame());
        }
        if (Controller.getInstance().getSignUpScene() == null) {
            Controller.getInstance().setSignUpScene(new Scene(Controller.getInstance().getSignUpFrame(), 350, 360));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Sign Up");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getSignUpScene());
        Controller.getInstance().getSignUpFrame().setStyle("-fx-background-image: url(\"./images/Mini.png\");");
    }

}
