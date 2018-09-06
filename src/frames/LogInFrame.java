/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.LogOutAction;
import actions.SignUpAction;
import controller.Connector;
import controller.Controller;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author nikol
 */
public class LogInFrame extends VBox {

    private final TextField userField = new TextField();
    private final Label userLabel = new Label("username:");
    private final TextField passField = new PasswordField();

    private final Label passLabel = new Label("password:");
    private final Button cancelButton = new Button("cancel");
    private final Button signButton = new Button("sign up");
    private final Button enterButton = new Button("log in");
    private final GridPane gridPane = new GridPane();
    private final HBox hBox = new HBox(30);

    public LogInFrame() {
        userLabel.setTextFill(Color.web("#ffffff"));
        passLabel.setTextFill(Color.web("#ffffff"));
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 5, 5));
        gridPane.add(userLabel, 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(passLabel, 0, 1);
        gridPane.add(passField, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        userField.setAlignment(Pos.CENTER_RIGHT);
        passField.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().add(gridPane);
        hBox.getChildren().addAll(cancelButton, signButton, enterButton);
        this.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        cancelButton.setOnAction(new LogOutAction());
        signButton.setOnAction(new SignUpAction());
        enterButton.setOnAction(e -> {
            String user = getUserField().getText();
            String pass = getPassField().getText();

            try {

                Connector.getInstance().getAllAccounts();
                Connector.getInstance().logIn(user, pass);
            } catch (SQLException ex) {
                Logger.getLogger(LogInFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public TextField getUserField() {
        return userField;
    }

    public TextField getPassField() {
        return passField;
    }

}
