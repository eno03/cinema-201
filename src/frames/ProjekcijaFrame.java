/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;


import actions.BackAdminAction;
import controller.Connector;
import data.Projekcija;
import exceptions.emptyString;
import exceptions.unallowedString;
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
public class ProjekcijaFrame extends HBox {

    private final TextField idField = new TextField();
    private final TextField filmIdField = new TextField();
    private final TextField datumField = new TextField();
    private final TextField karteField = new TextField();
    private final TextField cenaField = new TextField();
    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Projekcija> projekcijaTableView;
    private final Button backButton = new Button("Back");

    public ProjekcijaFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        gridPane.add(new Label("Id: "), 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(new Label("Film id: "), 0, 1);
        gridPane.add(filmIdField, 1, 1);
        gridPane.add(new Label("Datum pustanja: "), 0, 2);
        gridPane.add(datumField, 1, 2);
        gridPane.add(new Label("Broj karata: "), 0, 3);
        gridPane.add(karteField, 1, 3);
        gridPane.add(new Label("Cena: "), 0, 4);
        gridPane.add(cenaField, 1, 4);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        idField.setAlignment(Pos.CENTER_RIGHT);
        filmIdField.setAlignment(Pos.CENTER_RIGHT);
        datumField.setAlignment(Pos.CENTER_RIGHT);
        karteField.setAlignment(Pos.CENTER_RIGHT);
        cenaField.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);

        TableColumn<Projekcija, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Projekcija, Integer> filmColumn = new TableColumn<>("Film id");
        filmColumn.setCellValueFactory(new PropertyValueFactory<>("film_id"));
        TableColumn<Projekcija, String> datumColumn = new TableColumn<>("Datum pustanja");
        datumColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        TableColumn<Projekcija, Integer> karteColumn = new TableColumn<>("Broj karata");
        karteColumn.setCellValueFactory(new PropertyValueFactory<>("broj_karata"));
        TableColumn<Projekcija, Float> cenaColumn = new TableColumn<>("Cena");
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));

        projekcijaTableView = new TableView<>();
        projekcijaTableView.getColumns().addAll(idColumn, filmColumn, datumColumn, karteColumn, cenaColumn);
        this.getChildren().addAll(projekcijaTableView);
        backButton.setOnAction(new BackAdminAction());
        updateButton.setOnAction(e -> {

            controller.Connector.getInstance().updateProjekcija();
            controller.Connector.getInstance().refreshProjekcijas();
        });
        insertButton.setOnAction(e -> {
            controller.Connector.getInstance().addProjekcija();
            controller.Connector.getInstance().refreshProjekcijas();
        });
        deleteButton.setOnAction((event) -> {
            controller.Connector.getInstance().deleteProjekcija();
            controller.Connector.getInstance().refreshProjekcijas();
        });

        projekcijaTableView.setRowFactory(tv -> {
            TableRow<Projekcija> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Projekcija rowData = row.getItem();
                    idField.setText(rowData.getId() + "");
                    filmIdField.setText(rowData.getFilm_id() + "");
                    datumField.setText(rowData.getDatum());
                    karteField.setText(rowData.getBroj_karata() + "");
                    cenaField.setText(rowData.getCena() + "");
                }
            });
            return row;
        });
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getFilmIdField() {
        return filmIdField;
    }

    public TextField getDatumField() {
        return datumField;
    }

    public TextField getKarteField() {
        return karteField;
    }

    public TextField getCenaField() {
        return cenaField;
    }

    public TableView<Projekcija> getProjekcijaTableView() {
        return projekcijaTableView;
    }

    public void setProjekcijaTableView(TableView<Projekcija> projekcijaTableView) {
        this.projekcijaTableView = projekcijaTableView;
    }

    private void checkI(String id, String filmId, String datum, String karte, String cena) throws emptyString, unallowedString {

        if (filmId.isEmpty()) {
            throw new emptyString("Unesite id filma!");
        } else if (datum.isEmpty()) {
            throw new emptyString("Unesite datum!");
        } else if (karte.isEmpty()) {
            throw new emptyString("Unesite broj karata!");
        } else if (cena.isEmpty()) {
            throw new emptyString("Unesite cenu karte!");
        }

        for (Projekcija s : Connector.getInstance().getProjekcijas()) {
            if (s.getFilm_id() == Integer.parseInt(filmId) && s.getDatum().equals(datum)) {
                throw new unallowedString("Smer već postoji, samo poseduje drugi id!");
            }
        }

    }
}
