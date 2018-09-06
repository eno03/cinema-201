/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.AcountsFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class KorisniciButtonAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getAccountsFrame() == null) {
            Controller.getInstance().setAccountsFrame(new AcountsFrame());
        }
        if (Controller.getInstance().getAccountsScene()== null) {
            Controller.getInstance().setAccountsScene(new Scene(Controller.getInstance().getAccountsFrame(),  910, 320));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Accounts");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getAccountsScene());
        Controller.getInstance().getAccountsFrame().setStyle("-fx-background-color: gainsboro");
        Controller.getInstance().getAccountsFrame().getKorisniciTableView().setItems(Connector.getInstance().getAccounts());
    }

}
