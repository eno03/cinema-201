/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import actions.RezervacijeAction;
import actions.FilmButtonAction;
import actions.KorisniciButtonAction;
import actions.LogOutAction;
import actions.ProjekcijaAction;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author nikol
 */
public class AdminAccountFrame extends BorderPane {

    private GridPane gridPane = new GridPane();
    private Label accountLabel = new Label("Administrator: " + Controller.getInstance().getK().getUsername());
    private Button logOutbutton = new Button("Log Out");
    private final Button repertoarButton = new Button("Repertoar");
    private final Button rezervacijeButton = new Button("Rezervacije");
    private final Button korisniciButton = new Button("Korisnici");
    private final Button filmButton = new Button("Filmovi");
    private final Label filmLabel = new Label();
    private final Label korisniciLabel = new Label();
    private final Label repertoarLabel = new Label();
    private final Label rezervacijeLabel = new Label();
    private HBox accHBox = new HBox(10);

    public AdminAccountFrame() {
        accountLabel.setTextFill(Color.web("#ffffff"));
        accHBox.getChildren().addAll(accountLabel, logOutbutton);
        this.setTop(accHBox);
        accHBox.setAlignment(Pos.TOP_RIGHT);
        logOutbutton.setMaxHeight(15);
        logOutbutton.setOnAction(new LogOutAction());
        this.setPadding(new Insets(5));
        gridPane.add(repertoarLabel, 0, 0);
        repertoarLabel.setGraphic(new ImageView("./images/repertoar.png"));
        gridPane.add(repertoarButton, 0, 1);
        gridPane.add(filmLabel, 1, 0);
        filmLabel.setGraphic(new ImageView("./images/movie.png"));
        gridPane.add(filmButton, 1, 1);
        gridPane.add(korisniciLabel, 2, 0);
        korisniciLabel.setGraphic(new ImageView("./images/account.png"));
        gridPane.add(korisniciButton, 2, 1);
        gridPane.add(rezervacijeLabel, 3, 0);
        rezervacijeLabel.setGraphic(new ImageView("./images/reservation.png"));
        gridPane.add(rezervacijeButton, 3, 1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(40);
        gridPane.setVgap(10);
        this.setCenter(gridPane);
        korisniciButton.setOnAction(new KorisniciButtonAction());
        filmButton.setOnAction(new FilmButtonAction());
        repertoarButton.setOnAction(new ProjekcijaAction());
        rezervacijeButton.setOnAction(new RezervacijeAction());
    }

}
