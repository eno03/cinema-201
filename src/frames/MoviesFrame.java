/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.BackAdminAction;
import data.Film;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author nikol
 */
public class MoviesFrame extends HBox {

    private final TextField idField = new TextField();
    private final TextField nazivField = new TextField();
    private final ComboBox<String> zanrField = new ComboBox<>();
    private final TextField godinaField = new TextField();
    private final TextArea opisField = new TextArea();
    private final TextField glumciField = new TextField();
    private final TextField vremeTrajanjaField = new TextField();
    private final Button insertButton = new Button("Insert");
    private final Button deleteButton = new Button("Delete");
    private final Button updateButton = new Button("Update");
    private final GridPane gridPane = new GridPane();
    private final VBox vBox = new VBox(20);
    private final HBox hBox = new HBox(20);
    private TableView<Film> filmTableView;
    private final Button backButton = new Button("Back");

    public MoviesFrame() {
        this.setSpacing(20);
        this.setPadding(new Insets(20, 5, 10, 5));
        gridPane.add(new Label("Id: "), 0, 0);
        gridPane.add(idField, 1, 0);
        gridPane.add(new Label("Naziv: "), 0, 1);
        gridPane.add(nazivField, 1, 1);
        gridPane.add(new Label("Zanr: "), 0, 2);
        gridPane.add(zanrField, 1, 2);
        gridPane.add(new Label("Godina: "), 0, 3);
        gridPane.add(godinaField, 1, 3);
        gridPane.add(new Label("Opis: "), 0, 4);
        gridPane.add(opisField, 1, 4);
        gridPane.add(new Label("Glumci: "), 0, 5);
        gridPane.add(glumciField, 1, 5);
        gridPane.add(new Label("Trajanje: "), 0, 6);
        gridPane.add(vremeTrajanjaField, 1, 6);
        opisField.setMaxSize(200, 60);
        zanrField.setMinWidth(200);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        idField.setAlignment(Pos.CENTER_RIGHT);
        nazivField.setAlignment(Pos.CENTER_RIGHT);
        godinaField.setAlignment(Pos.CENTER_RIGHT);
        glumciField.setAlignment(Pos.CENTER_RIGHT);
        vremeTrajanjaField.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().addAll(insertButton, updateButton, deleteButton, backButton);
        vBox.getChildren().addAll(gridPane, hBox);
        vBox.setAlignment(Pos.CENTER);
        this.getChildren().addAll(vBox);
        zanrField.getItems().addAll("Akcija", "Avantura", "Komedija", "Drama", "Fantazija", "Horor", "Misterija", "Romantika", "Triler");

        TableColumn<Film, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Film, String> nazivColumn = new TableColumn<>("Naziv");
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        TableColumn<Film, String> zanrColumn = new TableColumn<>("Zanr");
        zanrColumn.setCellValueFactory(new PropertyValueFactory<>("zanr"));
        TableColumn<Film, Integer> godinaColumn = new TableColumn<>("Godina");
        godinaColumn.setCellValueFactory(new PropertyValueFactory<>("godina"));
        TableColumn<Film, String> opisColumn = new TableColumn<>("Opis");
        opisColumn.setCellValueFactory(new PropertyValueFactory<>("opis"));
        TableColumn<Film, String> glumciColumn = new TableColumn<>("Glumci");
        glumciColumn.setCellValueFactory(new PropertyValueFactory<>("glumci"));
        TableColumn<Film, String> trajanjeColumn = new TableColumn<>("Trajanje");
        trajanjeColumn.setCellValueFactory(new PropertyValueFactory<>("vremeTrajanja"));

        filmTableView = new TableView<>();
        filmTableView.getColumns().addAll(idColumn, nazivColumn, zanrColumn, godinaColumn, opisColumn, glumciColumn, trajanjeColumn);
        this.getChildren().addAll(filmTableView);
        backButton.setOnAction(new BackAdminAction());
        updateButton.setOnAction(e -> {
            controller.Connector.getInstance().updateMovie();
            controller.Connector.getInstance().refreshMovies();
        });
        insertButton.setOnAction(e -> {
            controller.Connector.getInstance().addMovie();
            controller.Connector.getInstance().refreshMovies();
        });
        deleteButton.setOnAction((event) -> {
            controller.Connector.getInstance().deleteMovie();
            controller.Connector.getInstance().refreshMovies();
        });

        filmTableView.setRowFactory(tv -> {
            TableRow<Film> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Film rowData = row.getItem();
                    idField.setText(rowData.getId() + "");
                    nazivField.setText(rowData.getNaziv());
                    zanrField.setValue(rowData.getZanr());
                    godinaField.setText(rowData.getGodina() + "");
                    opisField.setText(rowData.getOpis());
                    glumciField.setText(rowData.getGlumci());
                    vremeTrajanjaField.setText(rowData.getVremeTrajanja());
                }
            });
            return row;
        });
    }

    public TableView<Film> getFilmTableView() {
        return filmTableView;
    }

    public void setFilmTableView(TableView<Film> filmTableView) {
        this.filmTableView = filmTableView;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNazivField() {
        return nazivField;
    }

    public ComboBox<String> getZanrField() {
        return zanrField;
    }

    public TextField getGodinaField() {
        return godinaField;
    }

    public TextArea getOpisField() {
        return opisField;
    }

    public TextField getGlumciField() {
        return glumciField;
    }

    public TextField getVremeTrajanjaField() {
        return vremeTrajanjaField;
    }

}
