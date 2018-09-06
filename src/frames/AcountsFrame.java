/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAdminAction;
import data.Korisnik;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author nikol
 */
public class AcountsFrame extends HBox {

    private final TextField usernameField = new TextField();
    private final TextField passwordField = new TextField();
    private final TextField statusField = new TextField();
    private final TextField imeField = new TextField();
    private final TextField prezimeField = new TextField();
    private final TextField eMailField = new TextField();
    private final TextField kontaktField = new TextField();
    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final Button backButton = new Button("Back");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Korisnik> korisniciTableView;

    public AcountsFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        gridPane.add(new Label("username: "), 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(new Label("password: "), 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(new Label("Status: "), 0, 2);
        gridPane.add(statusField, 1, 2);
        gridPane.add(new Label("Ime: "), 0, 3);
        gridPane.add(imeField, 1, 3);
        gridPane.add(new Label("Prezime: "), 0, 4);
        gridPane.add(prezimeField, 1, 4);
        gridPane.add(new Label("E-mail: "), 0, 5);
        gridPane.add(eMailField, 1, 5);
        gridPane.add(new Label("Kontakt: "), 0, 6);
        gridPane.add(kontaktField, 1, 6);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        usernameField.setAlignment(Pos.CENTER_RIGHT);
        passwordField.setAlignment(Pos.CENTER_RIGHT);
        imeField.setAlignment(Pos.CENTER_RIGHT);
        prezimeField.setAlignment(Pos.CENTER_RIGHT);
        statusField.setAlignment(Pos.CENTER_RIGHT);
        eMailField.setAlignment(Pos.CENTER_RIGHT);
        kontaktField.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);

        TableColumn<Korisnik, String> userColumn = new TableColumn<>("username");
        userColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<Korisnik, String> passColumn = new TableColumn<>("password");
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        TableColumn<Korisnik, Integer> statusColumn = new TableColumn<>("status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Korisnik, String> imeColumn = new TableColumn<>("Ime");
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        TableColumn<Korisnik, String> prezimeColumn = new TableColumn<>("Prezime");
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        TableColumn<Korisnik, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Korisnik, String> kontaktColumn = new TableColumn<>("Kontakt");
        kontaktColumn.setCellValueFactory(new PropertyValueFactory<>("kontaktTelefon"));
        korisniciTableView = new TableView<>();
        korisniciTableView.getColumns().addAll(userColumn, passColumn, statusColumn, imeColumn, prezimeColumn, emailColumn, kontaktColumn);
        this.getChildren().addAll(korisniciTableView);
        backButton.setOnAction(new BackAdminAction());
        updateButton.setOnAction(e -> {
            controller.Connector.getInstance().updateAccount();
            controller.Connector.getInstance().refreshAccounts();
        });
        insertButton.setOnAction(e -> {
            controller.Connector.getInstance().addAccount();
            controller.Connector.getInstance().refreshAccounts();
        });
        deleteButton.setOnAction((event) -> {
            controller.Connector.getInstance().deleteAccount();
            controller.Connector.getInstance().refreshAccounts();
        });

        korisniciTableView.setRowFactory(tv -> {
            TableRow<Korisnik> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Korisnik rowData = row.getItem();
                    usernameField.setText(rowData.getUsername() + "");
                    passwordField.setText(rowData.getPassword() + "");
                    statusField.setText(rowData.getStatus() + "");
                    imeField.setText(rowData.getIme());
                    prezimeField.setText(rowData.getPrezime());
                    eMailField.setText(rowData.geteMail());
                    kontaktField.setText(rowData.getKontaktTelefon());

                }
            });
            return row;
        });
    }

    public TableView<Korisnik> getKorisniciTableView() {
        return korisniciTableView;
    }

    public void setKorisniciTableView(TableView<Korisnik> korisniciTableView) {
        this.korisniciTableView = korisniciTableView;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
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

    public TextField getStatusField() {
        return statusField;
    }
    
}
