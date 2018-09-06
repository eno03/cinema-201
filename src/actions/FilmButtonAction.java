/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import frames.MoviesFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 *
 * @author nikol
 */
public class FilmButtonAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        if (Controller.getInstance().getMoviesFrame() == null) {
            Controller.getInstance().setMoviesFrame(new MoviesFrame());
        }
        if (Controller.getInstance().getMoviesScene() == null) {
            Controller.getInstance().setMoviesScene(new Scene(Controller.getInstance().getMoviesFrame(), 880, 320));
        }
        Controller.getInstance().getPrimaryStage().setTitle("SE201|Movies");
        Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getMoviesScene());
        Controller.getInstance().getMoviesFrame().setStyle("-fx-background-color: gainsboro");
        Connector.getInstance().getAllMovies();
        Controller.getInstance().getMoviesFrame().getFilmTableView().setItems(Connector.getInstance().getMovies());

    }

}
