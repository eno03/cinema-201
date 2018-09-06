/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.Connector;
import controller.Controller;
import frames.MainFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nikol
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainFrame mainFrame = new MainFrame();
        Scene mainScene = new Scene(mainFrame, 812, 640);

        Controller.getInstance().setPrimaryStage(primaryStage);
        Controller.getInstance().setMainFrame(mainFrame);
        Controller.getInstance().setMainScene(mainScene);
        Controller.getInstance().getPrimaryStage().setScene(mainScene);
        Controller.getInstance().getMainFrame().setStyle("-fx-background-image: url(\"./images/Cinema201.png\");");
        primaryStage.setTitle("SE201|Bioskop");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);
        primaryStage.show();
Connector.getInstance().getAllAccounts();
        Connector.getInstance().getAllMovies();
        Controller.getInstance().getMainFrame().getMovieTableView().setItems(Connector.getInstance().getMovies());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
