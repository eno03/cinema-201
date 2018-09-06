/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.LogInAction;
import actions.LogOutAction;
import controller.Connector;
import data.Korisnik;
import exceptions.emptyString;
import exceptions.unallowedString;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author nikol
 */
public class SignUpFrame extends VBox {

    private final Label lab = new Label();
    private final TextField userField = new TextField();
    private final TextField imeField = new TextField();
    private final TextField prezimeField = new TextField();
    private final TextField eMailField = new TextField();
    private final TextField kontaktField = new TextField();
    private final TextField pass1Field = new PasswordField();
    private final TextField pass2Field = new PasswordField();
    private final Label userField1 = new Label("username: ");
    private final Label imeField1 = new Label("ime: ");
    private final Label prezimeField1 = new Label("prezime: ");
    private final Label eMailField1 = new Label("email: ");
    private final Label kontaktField1 = new Label("kontakt");
    private final Label pass1Field1 = new Label("password");
    private final Label pass2Field1 = new Label("retype password");
    private final Button cancelButton = new Button("cancel");
    private final Button logInButton = new Button("log in");
    private final Button enterButton = new Button("sign up");
    private final GridPane gridPane = new GridPane();
    private final HBox hBox = new HBox(30);

    public SignUpFrame() {
        userField1.setTextFill(Color.web("#ffffff"));
        imeField1.setTextFill(Color.web("#ffffff"));
        prezimeField1.setTextFill(Color.web("#ffffff"));
        eMailField1.setTextFill(Color.web("#ffffff"));
        kontaktField1.setTextFill(Color.web("#ffffff"));
        pass1Field1.setTextFill(Color.web("#ffffff"));
        pass2Field1.setTextFill(Color.web("#ffffff"));
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 5, 5));
        lab.setGraphic(new ImageView("./images/user.png"));
        gridPane.add(lab, 0, 0);
        gridPane.add(userField1, 0, 1);
        gridPane.add(userField, 1, 1);
        gridPane.add(imeField1, 0, 2);
        gridPane.add(imeField, 1, 2);
        gridPane.add(prezimeField1, 0, 3);
        gridPane.add(prezimeField, 1, 3);
        gridPane.add(eMailField1, 0, 4);
        gridPane.add(eMailField, 1, 4);
        gridPane.add(kontaktField1, 0, 5);
        gridPane.add(kontaktField, 1, 5);
        gridPane.add(new Label(), 0, 6);
        gridPane.add(new Label(), 1, 6);
        gridPane.add(pass1Field1, 0, 7);
        gridPane.add(pass1Field, 1, 7);
        gridPane.add(pass2Field1, 0, 8);
        gridPane.add(pass2Field, 1, 8);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        userField.setAlignment(Pos.CENTER_RIGHT);
        pass1Field.setAlignment(Pos.CENTER_RIGHT);
        pass2Field.setAlignment(Pos.CENTER_RIGHT);
        imeField.setAlignment(Pos.CENTER_RIGHT);
        prezimeField.setAlignment(Pos.CENTER_RIGHT);
        eMailField.setAlignment(Pos.CENTER_RIGHT);
        kontaktField.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().add(gridPane);
        hBox.getChildren().addAll(cancelButton, logInButton, enterButton);
        this.getChildren().add(hBox);
        hBox.setAlignment(Pos.CENTER);
        cancelButton.setOnAction(new LogOutAction());
        logInButton.setOnAction(new LogInAction());
        enterButton.setOnAction(e -> {
            if (pass1Field.getText().equals(pass2Field.getText())) {
                try {
                    checkI(userField.getText(), imeField.getText(), prezimeField.getText(), eMailField.getText(), kontaktField.getText(), pass1Field.getText(), pass2Field.getText());
                    Connector.getInstance().singUp(userField.getText(), pass1Field.getText(), imeField.getText(), prezimeField.getText(), eMailField.getText(), kontaktField.getText());
                    JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali! Sada mozete pristupiti svom nalogu..");
                } catch (emptyString | unallowedString ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Password se ne podudara sa zeljenim!");
                pass2Field.clear();
            }
        });
    }

    public TextField getUserField() {
        return userField;
    }

    public TextField getImeField() {
        return imeField;
    }

    public TextField getPrezimeField() {
        return prezimeField;
    }

    public TextField geteMailField() {
        return eMailField;
    }

    public TextField getKontaktField() {
        return kontaktField;
    }

    public TextField getPass1Field() {
        return pass1Field;
    }

    public TextField getPass2Field() {
        return pass2Field;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void checkI(String user, String ime, String prezime, String email, String kontakt, String pass1, String pass2) throws emptyString, unallowedString {

        if (user.isEmpty()) {
            throw new emptyString("Unesite username!");
        } else if (ime.isEmpty()) {
            throw new emptyString("Unesite ime!");
        } else if (prezime.isEmpty()) {
            throw new emptyString("Unesite prezime!");
        } else if (email.isEmpty()) {
            throw new emptyString("Unesite email!");
        } else if (kontakt.isEmpty()) {
            throw new emptyString("Unesite kontakt telefon!");
        } else if (pass1.isEmpty() && pass2.isEmpty()) {
            throw new emptyString("Unesite password!");
        }

        for (Korisnik s : Connector.getInstance().getAccounts()) {
            if (s.getUsername().equals(user)) {
                throw new unallowedString("Korisnicko ime je zauzeto!");
            }
        }

        if (!ime.matches("[a-zA-Z]+")) {
            throw new unallowedString("Ime ne sme sadrzati nista osim slova!");
        }
        if (!prezime.matches("[a-zA-Z]+")) {
            throw new unallowedString("Prezime ne sme sadrzati nista osim slova!");
        }
    }

}
