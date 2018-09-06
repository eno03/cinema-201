/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.LogInAction;
import actions.SignUpAction;
import controller.Connector;
import data.Film;
import data.Projekcija;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author nikol
 */
public class MainFrame extends BorderPane {

    private Label accountLabel = new Label("Account: ");
    private Button singUpButton = new Button("Sign Up");
    private Button logInButton = new Button("Log In");
    private HBox accHBox = new HBox(10);
    private TableView<Film> movieTableView;
    private TableView<Projekcija> repertoarTableView;
    private TextArea area = new TextArea("Dupli klik na zeljeni film za detalje o izabranom filmu...");
    private Label lab = new Label("Raspored");
    private HBox hbmain = new HBox(10);
    private VBox vbl = new VBox(5);
    private VBox vbr = new VBox(10);
    private GridPane gp = new GridPane();
    private final TextField userField = new TextField();
    private TextField imeField = new TextField();
    private TextField prezimeField = new TextField();
    private TextField sedisteField = new TextField();
    private TextField parterField = new TextField();
    private final ComboBox<String> zanrField = new ComboBox<>();
    private Label rezervisiButton = new Label("Morate se ulogovati...");
    private Label rezervacijaLabel = new Label("REZERVACIJA");

    public MainFrame() {
        rezervacijaLabel.setTextFill(Color.web("#ffffff"));
        rezervisiButton.setTextFill(Color.web("#ffffff"));
        accountLabel.setTextFill(Color.web("#ffffff"));
        lab.setTextFill(Color.web("#ffffff"));
        accHBox.setStyle("-fx-background-image: url(\"./images/banner.png\");");
        this.setPadding(new Insets(5));
        hbmain.setPadding(new Insets(10));
        zanrField.setValue("Sve");
        zanrField.getItems().addAll("Sve", "Akcija", "Avantura", "Komedija", "Drama", "Fantazija", "Horor", "Misterija", "Romantika", "Triler");
        zanrField.setMaxHeight(20);
        accHBox.getChildren().addAll(accountLabel, singUpButton, logInButton);
        this.setTop(accHBox);
        this.setCenter(hbmain);
        accHBox.setAlignment(Pos.TOP_RIGHT);
        accHBox.setMinHeight(150);

        logInButton.setOnAction(new LogInAction());
        singUpButton.setOnAction(new SignUpAction());
        gp.add(userField, 0, 1);
        userField.setPromptText("niste ulogovani...");
        userField.setEditable(false);
        gp.add(imeField, 0, 2);
        imeField.setPromptText("ime");
        gp.add(prezimeField, 1, 2);
        prezimeField.setPromptText("prezime");
        sedisteField.setPromptText("sediste");
        sedisteField.setEditable(false);
        gp.add(sedisteField, 0, 3);
        parterField.setPromptText("parter");
        gp.add(parterField, 1, 3);
        parterField.setEditable(false);
        gp.setVgap(20);
        gp.setHgap(90);
        TableColumn<Film, String> nazivColumn = new TableColumn<>("Naziv");
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        nazivColumn.setMinWidth(230);
        TableColumn<Film, String> zanrColumn = new TableColumn<>("Zanr");
        zanrColumn.setCellValueFactory(new PropertyValueFactory<>("zanr"));
        TableColumn<Film, Integer> godinaColumn = new TableColumn<>("Godina");
        godinaColumn.setCellValueFactory(new PropertyValueFactory<>("godina"));;

        TableColumn<Projekcija, String> datumPColumn = new TableColumn<>("Datum");
        datumPColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        datumPColumn.setMinWidth(230);
        TableColumn<Projekcija, Integer> brojColumn = new TableColumn<>("Broj karata");
        brojColumn.setCellValueFactory(new PropertyValueFactory<>("broj_karata"));
        TableColumn<Projekcija, Float> cenaColumn = new TableColumn<>("Cena");
        cenaColumn.setCellValueFactory(new PropertyValueFactory<>("cena"));;

        movieTableView = new TableView<>();
        repertoarTableView = new TableView<>();
        repertoarTableView.setMinSize(390, 220);
        repertoarTableView.setMaxSize(390, 220);
        movieTableView.setMinSize(390, 220);
        movieTableView.setMaxSize(390, 220);
        area.setMinSize(390, 220);
        area.setMaxSize(390, 220);
        area.setEditable(false);
        area.setWrapText(true);
        movieTableView.getColumns().addAll(nazivColumn, godinaColumn, zanrColumn);
        repertoarTableView.getColumns().addAll(datumPColumn, brojColumn, cenaColumn);
        hbmain.getChildren().addAll(vbl, vbr);

        vbl.getChildren().addAll(zanrField, movieTableView, area);
        vbl.setAlignment(Pos.TOP_CENTER);
        vbr.getChildren().addAll(lab, repertoarTableView, rezervacijaLabel, gp, rezervisiButton);
        vbr.setAlignment(Pos.TOP_CENTER);

        movieTableView.setRowFactory(tv -> {
            TableRow<Film> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Film rowData = row.getItem();
                    area.setText(rowData.print());
                    repertoarTableView.getItems().clear();
                    Connector.getInstance().getProjekcija(rowData);
                    repertoarTableView.setItems(Connector.getInstance().getProjekcijaP());
                }
            });
            return row;
        });

        zanrField.setOnAction(e -> {
            movieTableView.getItems().clear();
            Connector.getInstance().getMovies().clear();
            if (zanrField.getValue().equalsIgnoreCase("sve")) {
                Connector.getInstance().getAllMovies();
            } else {
                Connector.getInstance().getMovies(zanrField.getValue());
            }
            movieTableView.setItems(Connector.getInstance().getMovies());
        });
    }

    public TableView<Film> getMovieTableView() {
        return movieTableView;
    }

    public void setMovieTableView(TableView<Film> movieTableView) {
        this.movieTableView = movieTableView;
    }

}
