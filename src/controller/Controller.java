/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import frames.ProjekcijaFrame;
import data.Korisnik;
import frames.AccountFrame;
import frames.AcountsFrame;
import frames.AdminAccountFrame;
import frames.LogInFrame;
import frames.MainFrame;
import frames.MoviesFrame;
import frames.RezervacijeFrame;
import frames.SignUpFrame;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nikol
 */
public class Controller {

    private static Controller instance = null;
    private Stage primaryStage;
    private Korisnik k;

    private Scene mainScene;
    private MainFrame mainFrame;
    private Scene loginScene;
    private LogInFrame loginFrame;
    private AdminAccountFrame accountFrame;
    private Scene accountScene;
    private AcountsFrame accountsFrame;
    private Scene accountsScene;
    private MoviesFrame moviesFrame;
    private Scene moviesScene;
    private ProjekcijaFrame projekcijaFrame;
    private Scene projekcijaScene;
    private RezervacijeFrame rezervacijeFrame;
    private Scene rezervacijeScene;
    private SignUpFrame signUpFrame;
    private Scene signUpScene;
    private AccountFrame accountAFrame;
    private Scene accountAScene;
    

    private Controller() {
        mainScene = null;
        mainFrame = null;
        loginScene = null;
        loginFrame = null;
        accountFrame = null;
        accountScene = null;
        accountsFrame = null;
        accountsScene = null;
        moviesFrame = null;
        moviesScene = null;
        projekcijaFrame = null;
        projekcijaScene = null;
        rezervacijeFrame = null;
        rezervacijeScene = null;
        signUpFrame = null;
        signUpScene = null;
        accountAFrame = null;
        accountAScene = null;
        k = null;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    public LogInFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(LogInFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public Korisnik getK() {
        return k;
    }

    public void setK(Korisnik k) {
        this.k = k;
    }

    public AdminAccountFrame getAccountFrame() {
        return accountFrame;
    }

    public void setAccountFrame(AdminAccountFrame accountFrame) {
        this.accountFrame = accountFrame;
    }

    public Scene getAccountScene() {
        return accountScene;
    }

    public void setAccountScene(Scene accountScene) {
        this.accountScene = accountScene;
    }

    public AcountsFrame getAccountsFrame() {
        return accountsFrame;
    }

    public void setAccountsFrame(AcountsFrame accountsFrame) {
        this.accountsFrame = accountsFrame;
    }

    public Scene getAccountsScene() {
        return accountsScene;
    }

    public void setAccountsScene(Scene accountsScene) {
        this.accountsScene = accountsScene;
    }

    public MoviesFrame getMoviesFrame() {
        return moviesFrame;
    }

    public void setMoviesFrame(MoviesFrame moviesFrame) {
        this.moviesFrame = moviesFrame;
    }

    public Scene getMoviesScene() {
        return moviesScene;
    }

    public void setMoviesScene(Scene moviesScene) {
        this.moviesScene = moviesScene;
    }

    public ProjekcijaFrame getProjekcijaFrame() {
        return projekcijaFrame;
    }

    public void setProjekcijaFrame(ProjekcijaFrame projekcijaFrame) {
        this.projekcijaFrame = projekcijaFrame;
    }

    public Scene getProjekcijaScene() {
        return projekcijaScene;
    }

    public void setProjekcijaScene(Scene projekcijaScene) {
        this.projekcijaScene = projekcijaScene;
    }

    public RezervacijeFrame getRezervacijeFrame() {
        return rezervacijeFrame;
    }

    public void setRezervacijeFrame(RezervacijeFrame rezervacijeFrame) {
        this.rezervacijeFrame = rezervacijeFrame;
    }

    public Scene getRezervacijeScene() {
        return rezervacijeScene;
    }

    public void setRezervacijeScene(Scene rezervacijeScene) {
        this.rezervacijeScene = rezervacijeScene;
    }

    public SignUpFrame getSignUpFrame() {
        return signUpFrame;
    }

    public void setSignUpFrame(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;
    }

    public Scene getSignUpScene() {
        return signUpScene;
    }

    public void setSignUpScene(Scene signUpScene) {
        this.signUpScene = signUpScene;
    }

    public AccountFrame getAccountAFrame() {
        return accountAFrame;
    }

    public void setAccountAFrame(AccountFrame accountAFrame) {
        this.accountAFrame = accountAFrame;
    }

    public Scene getAccountAScene() {
        return accountAScene;
    }

    public void setAccountAScene(Scene accountAScene) {
        this.accountAScene = accountAScene;
    }

}
