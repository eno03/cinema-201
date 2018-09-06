/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.IzborSedistaAction;
import actions.RezervacijaAction;
import actions.LogOutAction;
import controller.Connector;
import controller.Controller;
import data.Film;
import data.Projekcija;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class AccountFrame extends BorderPane {

    private Label accountLabel = new Label("Account: " + Controller.getInstance().getK().getUsername());
    private Label rezervacijaLabel = new Label("REZERVISI");
    private Button logOutbutton = new Button("Log out");
    private HBox accHBox = new HBox(10);
    private TableView<Film> movieTableView;
    private TableView<Projekcija> repertoarTableView;
    private TextArea area = new TextArea("Dupli klik na zeljeni film za detalje o izabranom filmu...");
    private Label lab = new Label("Raspored");
    private HBox hbmain = new HBox(10);
    private VBox vbl = new VBox(5);
    private VBox vbr = new VBox(10);
    private VBox vbP = new VBox(5);
    private VBox vbc = new VBox(5);
    private HBox hbB = new HBox(180);
    private GridPane gp = new GridPane();
    private final TextField userField = new TextField(Controller.getInstance().getK().getUsername());
    private final TextField cenaField = new TextField();
    private TextField imeField = new TextField(Controller.getInstance().getK().getIme());
    private TextField prezimeField = new TextField(Controller.getInstance().getK().getPrezime());
    private TextField redField = new TextField();
    private TextField sedisteField = new TextField();
    private final ComboBox<String> zanrField = new ComboBox<>();
    private RadioButton student_rb = new RadioButton("Student");
    private RadioButton retiree_rb = new RadioButton("Penzioner");
    private ToggleGroup discount_group = new ToggleGroup();
    private VBox discount = new VBox();
    private Button rezervisiButton = new Button("Rezervisi");
    private Button mestoButton = new Button("Sediste");
    private HBox red_sediste = new HBox(25);
    private Film izabraniFilm;
    private Projekcija izabranaProjekcija;

    public AccountFrame() {

        student_rb.setTextFill(Color.web("#ffffff"));
        retiree_rb.setTextFill(Color.web("#ffffff"));
        student_rb.setUserData("Student");
        retiree_rb.setUserData("Penzioner");
        vbP.getChildren().addAll(imeField, prezimeField);
        vbc.getChildren().addAll(userField, cenaField);
        rezervacijaLabel.setTextFill(Color.web("#ffffff"));
        accountLabel.setTextFill(Color.web("#ffffff"));
        lab.setTextFill(Color.web("#ffffff"));
        hbB.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5));
        hbmain.setPadding(new Insets(10));
        zanrField.setValue("Sve");
        zanrField.getItems().addAll("Sve", "Akcija", "Avantura", "Komedija", "Drama", "Fantazija", "Horor", "Misterija", "Romantika", "Triler");
        zanrField.setMaxHeight(20);
        accHBox.getChildren().addAll(accountLabel, logOutbutton);

        this.setTop(accHBox);
        this.setCenter(hbmain);
        accHBox.setAlignment(Pos.TOP_RIGHT);
        accHBox.setMinSize(800, 150);
        logOutbutton.setOnAction(new LogOutAction());
        cenaField.setPromptText("Cena...");
        cenaField.setEditable(false);
        gp.add(vbc, 0, 1);
        userField.setPromptText("niste ulogovani...");
        userField.setEditable(false);
        gp.add(vbP, 1, 1);
        imeField.setPromptText("ime");
        gp.add(discount, 1, 2);
        prezimeField.setPromptText("prezime");
        redField.setPromptText("red");
        sedisteField.setPromptText("sediste");
        sedisteField.setEditable(false);
        gp.add(red_sediste, 0, 2);
        sedisteField.setMaxWidth(60);
        redField.setMaxWidth(60);
        redField.setEditable(false);
        red_sediste.getChildren().addAll(redField, sedisteField);
        red_sediste.setAlignment(Pos.CENTER);
        gp.setVgap(20);
        gp.setHgap(90);

        discount.setSpacing(5);
        discount.setPadding(new Insets(5, 5, 5, 5));
        discount.getChildren().addAll(student_rb, retiree_rb);
        student_rb.setToggleGroup(discount_group);
        retiree_rb.setToggleGroup(discount_group);
        student_rb.setOnAction((ActionEvent e) -> {
            cenaField.setText(izracunajCenu(repertoarTableView.getSelectionModel().getSelectedItem().getCena(), "Student") + "");
        });
        retiree_rb.setOnAction((ActionEvent e) -> {
            cenaField.setText(izracunajCenu(repertoarTableView.getSelectionModel().getSelectedItem().getCena(), "Penzioner") + "");
        });

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
        hbmain.getChildren().addAll(vbl, vbr, hbB);
        hbB.getChildren().setAll(mestoButton, rezervisiButton);
        vbl.getChildren().addAll(zanrField, movieTableView, area);
        vbl.setAlignment(Pos.TOP_CENTER);
        vbr.getChildren().addAll(lab, repertoarTableView, rezervacijaLabel, gp, hbB);
        vbr.setAlignment(Pos.TOP_CENTER);
        mestoButton.setOnAction(new IzborSedistaAction());
        mestoButton.setDisable(true);
        rezervisiButton.setDisable(true);
        rezervisiButton.setOnAction(new RezervacijaAction());
        movieTableView.setRowFactory(tv -> {
            TableRow<Film> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Film rowData = row.getItem();
                    izabraniFilm = rowData;
                    area.setText(rowData.print());
                    repertoarTableView.getItems().clear();
                    Connector.getInstance().getProjekcija(rowData);
                    repertoarTableView.setItems(Connector.getInstance().getProjekcijaP());
                }
            });
            return row;
        });
        repertoarTableView.setRowFactory(tv -> {
            TableRow<Projekcija> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Projekcija rowData = row.getItem();
                    izabranaProjekcija = rowData;
                    mestoButton.setDisable(false);
                    cenaField.setText(rowData.getCena() + "");
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

    public Film getIzabraniFilm() {
        return izabraniFilm;
    }

    public Projekcija getIzabranaProjekcija() {
        return izabranaProjekcija;
    }

    public TextField getSedisteField() {
        return sedisteField;
    }

    public void setSedisteField(TextField sedisteField) {
        this.sedisteField = sedisteField;
    }

    public HBox getRed_sediste() {
        return red_sediste;
    }

    public TextField getRedField() {
        return redField;
    }

    public void setRedField(TextField redField) {
        this.redField = redField;
    }

    public void setRed_sediste(HBox red_sediste) {
        this.red_sediste = red_sediste;
    }

    public Button getRezervisiButton() {
        return rezervisiButton;
    }

    public void setRezervisiButton(Button rezervisiButton) {
        this.rezervisiButton = rezervisiButton;
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

    public static float izracunajCenu(float cena, String popust) {
        float pom = cena;
        if (popust.equals("Student")) {
            pom -= cena * (20.0f / 100.0f);
        } else if (popust.equals("Penzioner")) {
            pom -= cena * (10.0f / 100.0f);
        }
        return pom;
    }

    public TextField getCenaField() {
        return cenaField;
    }

    public ToggleGroup getDiscount_group() {
        return discount_group;
    }

}
