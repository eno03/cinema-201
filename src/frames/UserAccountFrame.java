/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.LogOutAction;
import controller.Controller;
import data.Film;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author nikol
 */
public class UserAccountFrame extends BorderPane {

    private Label accountLabel = new Label("Account: ");
    private Label accountInfoLabel = new Label(Controller.getInstance().getK().getUsername());
    private Button logOutbutton = new Button("Log Out");
    private HBox accHBox = new HBox(10);
    private Label repertoarLabel = new Label("Svi filmovi: ");
    private TableView<Film> tableView;

    public UserAccountFrame() {
        accHBox.getChildren().addAll(accountLabel, accountInfoLabel, logOutbutton);
        this.setTop(accHBox);
        accHBox.setAlignment(Pos.TOP_RIGHT);
        logOutbutton.setOnAction(new LogOutAction());
    }
}
