/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import frames.AdminAccountFrame;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class BackAdminAction implements EventHandler<ActionEvent> {

    public BackAdminAction() {
    }

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getAccountFrame() == null) {
            Controller.getInstance().setAccountFrame(new AdminAccountFrame());
        }
        if (Controller.getInstance().getAccountScene() == null) {
            Controller.getInstance().setAccountScene(new Scene(Controller.getInstance().getAccountFrame(), 660, 260));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Bioskop");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getAccountScene());
        Controller.getInstance().getAccountFrame().setStyle("-fx-background-image: url(\"./images/admin.png\");");
    }

}
