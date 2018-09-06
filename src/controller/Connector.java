/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import frames.AccountFrame;
import actions.LogInAction;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import data.Korisnik;
import frames.AdminAccountFrame;
import data.Film;
import data.Karta;
import data.Projekcija;
import frames.OdabirMestaFrame;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javax.swing.JOptionPane;

/**
 *
 * @author nikol
 */
public class Connector {

    private static Connector instance = null;
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet result;
    private final String url = "jdbc:mysql://localhost/se201_cinema";
    private final String user = "root";
    private final String pass = "";
    private String query = "";
    private ObservableList<Projekcija> projekcijas;
    private ObservableList<Film> movies;
    private ObservableList<Korisnik> accounts;
    private ObservableList<Karta> kartas = FXCollections.observableArrayList();
    private ObservableList<Projekcija> projekcijaP;

    public static Connector getInstance() {
        if (instance == null) {
            instance = new Connector();
        }
        return instance;
    }

    private Connector() {

        try {
            connection = (Connection) DriverManager.getConnection(url, user, pass);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo pristupiti bazi podataka!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnnection() throws SQLException {
        connection.close();
    }

    public void logIn(String user, String pass) throws SQLException {
        query = "SELECT * FROM `korisnik` WHERE `username` = ? AND `password` = ?";
        Statement statement;
        try {

            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet sc = ps.executeQuery();
            if (sc.next()) {
                String username = sc.getString(1);
                String password = sc.getString(2);
                int status = sc.getInt(3);
                String ime = sc.getString(4);
                String prezime = sc.getString(5);
                String eMail = sc.getString(6);
                String kontaktTelefon = sc.getString(7);
                Korisnik k = new Korisnik(username, password, status, ime, prezime, eMail, kontaktTelefon);
                if (Controller.getInstance().getK() == null) {
                    Controller.getInstance().setK(k);
                }
                if (status == 0) {
                    if (Controller.getInstance().getAccountFrame() == null) {
                        Controller.getInstance().setAccountFrame(new AdminAccountFrame());
                    }
                    if (Controller.getInstance().getAccountScene() == null) {
                        Controller.getInstance().setAccountScene(new Scene(Controller.getInstance().getAccountFrame(), 660, 260));
                    }
                    Controller.getInstance().getPrimaryStage().setTitle("SE201|Bioskop");
                    Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getAccountScene());
                    Controller.getInstance().getAccountFrame().setStyle("-fx-background-image: url(\"./images/admin.png\");");
                } else {
                    if (Controller.getInstance().getAccountAFrame() == null) {
                        Controller.getInstance().setAccountAFrame(new AccountFrame());
                    }
                    if (Controller.getInstance().getAccountAScene() == null) {
                        Controller.getInstance().setAccountAScene(new Scene(Controller.getInstance().getAccountAFrame(), 815, 640));
                    }
                    Controller.getInstance().getPrimaryStage().setTitle("SE201|Bioskop");
                    Controller.getInstance().getPrimaryStage().setScene(Controller.getInstance().getAccountAScene());
                    Controller.getInstance().getAccountAFrame().setStyle("-fx-background-image: url(\"./images/Cinema201.png\");");

                    Connector.getInstance().getAllMovies();
                    Controller.getInstance().getAccountAFrame().getMovieTableView().setItems(Connector.getInstance().getMovies());
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(LogInAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAccount() {
        query = "UPDATE `korisnik` SET `password`=?,`status_id`=?,`ime`=?,`prezime`=?,`email`=?,`kontakt`=? WHERE `korisnik`.`username` = ?";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setString(1, Controller.getInstance().getAccountsFrame().getPasswordField().getText());
            ps.setInt(2, Integer.parseInt(Controller.getInstance().getAccountsFrame().getStatusField().getText()));
            ps.setString(3, Controller.getInstance().getAccountsFrame().getImeField().getText());
            ps.setString(4, Controller.getInstance().getAccountsFrame().getPrezimeField().getText());
            ps.setString(5, Controller.getInstance().getAccountsFrame().geteMailField().getText());
            ps.setString(6, Controller.getInstance().getAccountsFrame().getKontaktField().getText());
            ps.setString(7, Controller.getInstance().getAccountsFrame().getUsernameField().getText());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addAccount() {
        query = "INSERT INTO `korisnik` (`username`, `password`, `status_id`, `ime`, `prezime`, `email`, `kontakt`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getAccountsFrame().getUsernameField().getText());
            ps.setString(2, Controller.getInstance().getAccountsFrame().getPasswordField().getText());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getAccountsFrame().getStatusField().getText()));
            ps.setString(4, Controller.getInstance().getAccountsFrame().getImeField().getText());
            ps.setString(5, Controller.getInstance().getAccountsFrame().getPrezimeField().getText());
            ps.setString(6, Controller.getInstance().getAccountsFrame().geteMailField().getText());
            ps.setString(7, Controller.getInstance().getAccountsFrame().getKontaktField().getText());

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAccount() {
        query = "DELETE FROM `korisnik` WHERE `korisnik`.`username` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, Controller.getInstance().getAccountsFrame().getUsernameField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllAccounts() {
        accounts = FXCollections.observableArrayList();
        query = "SELECT * FROM `korisnik`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            result = statement.executeQuery(query);
            while (result.next()) {

                String user = result.getString(1);
                String pass = result.getString(2);
                int status = result.getInt(3);
                String ime = result.getString(4);
                String prezime = result.getString(5);
                String email = result.getString(6);
                String kontakt = result.getString(7);
                Korisnik acc = new Korisnik(user, pass, status, ime, prezime, email, kontakt);
                accounts.add(acc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Korisnik> getAccounts() {
        return accounts;
    }

    public void refreshAccounts() {

        Controller.getInstance().getAccountsFrame().getKorisniciTableView().getItems().clear();
        accounts.clear();
        getAllAccounts();
        Controller.getInstance().getAccountsFrame().getKorisniciTableView().setItems(accounts);
    }

    public void getAllMovies() {
        movies = FXCollections.observableArrayList();
        query = "SELECT * FROM `film`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);
                String zanr = resultSet.getString(3);
                int godina = resultSet.getInt(4);
                String opis = resultSet.getString(5);
                String glumci = resultSet.getString(6);
                String trajanje = resultSet.getString(7);
                Film movie = new Film(id, naziv, zanr, godina, opis, glumci, trajanje);
                movie.toString();
                movies.add(movie);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Film> getMovies() {
        return movies;
    }

    public void updateMovie() {
        query = "UPDATE `film` SET `film_naziv` = ?, `film_zanr` = ?, `film_godina` = ?, `film_opis` = ?, `glavne_uloge` = ?, `vreme_trajanja` = ? WHERE `film`.`film_id` = ?";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setString(1, Controller.getInstance().getMoviesFrame().getNazivField().getText());
            ps.setString(2, Controller.getInstance().getMoviesFrame().getZanrField().getSelectionModel().getSelectedItem());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getMoviesFrame().getGodinaField().getText()));
            ps.setString(4, Controller.getInstance().getMoviesFrame().getOpisField().getText());
            ps.setString(5, Controller.getInstance().getMoviesFrame().getGlumciField().getText());
            ps.setString(6, Controller.getInstance().getMoviesFrame().getVremeTrajanjaField().getText());
            ps.setInt(7, Integer.parseInt(Controller.getInstance().getMoviesFrame().getIdField().getText()));
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshMovies() {

        Controller.getInstance().getMoviesFrame().getFilmTableView().getItems().clear();
        movies.clear();
        getAllMovies();
        Controller.getInstance().getMoviesFrame().getFilmTableView().setItems(movies);
    }

    public void addMovie() {
        query = "INSERT INTO `film` (`film_id`, `film_naziv`, `film_zanr`, `film_godina`, `film_opis`, `glavne_uloge`, `vreme_trajanja`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getMoviesFrame().getIdField().getText()));
            ps.setString(2, Controller.getInstance().getMoviesFrame().getNazivField().getText());
            ps.setString(3, Controller.getInstance().getMoviesFrame().getZanrField().getSelectionModel().getSelectedItem());
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getMoviesFrame().getGodinaField().getText()));
            ps.setString(5, Controller.getInstance().getMoviesFrame().getOpisField().getText());
            ps.setString(6, Controller.getInstance().getMoviesFrame().getGlumciField().getText());
            ps.setString(7, Controller.getInstance().getMoviesFrame().getVremeTrajanjaField().getText());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteMovie() {
        query = "DELETE FROM `film` WHERE `film`.`film_id` = ?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getMoviesFrame().getIdField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProjekcija() {
        query = "UPDATE `projekcija` SET `film_id` = ?, `datum` = ?, `broj_karata` = ?, `cena` = ? WHERE `projekcija`.`projekcija_id` = ?";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getFilmIdField().getText()));
            ps.setString(2, Controller.getInstance().getProjekcijaFrame().getDatumField().getText());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getKarteField().getText()));
            ps.setFloat(4, Float.parseFloat(Controller.getInstance().getProjekcijaFrame().getCenaField().getText()));
            ps.setInt(5, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getIdField().getText()));
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshProjekcijas() {

        Controller.getInstance().getProjekcijaFrame().getProjekcijaTableView().getItems().clear();
        projekcijas.clear();
        getAllProjekcijas();
        Controller.getInstance().getProjekcijaFrame().getProjekcijaTableView().setItems(projekcijas);

    }

    public void addProjekcija() {
        query = "INSERT INTO `projekcija` (`projekcija_id` ,`film_id` ,`datum` ,`broj_karata` ,`cena`)"
                + " VALUES (?, ?, ?, ?, ?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getIdField().getText()));
            ps.setInt(2, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getFilmIdField().getText()));
            ps.setString(3, Controller.getInstance().getProjekcijaFrame().getDatumField().getText());
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getKarteField().getText()));
            ps.setFloat(5, Float.parseFloat(Controller.getInstance().getProjekcijaFrame().getCenaField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProjekcija() {
        query = "DELETE FROM `projekcija` WHERE `projekcija`.`projekcija_id` =?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(Controller.getInstance().getProjekcijaFrame().getIdField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllProjekcijas() {
        projekcijas = FXCollections.observableArrayList();
        query = "SELECT * FROM `projekcija`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                int filmId = resultSet.getInt(2);
                String datum_vreme = resultSet.getString(3);
                int karte = resultSet.getInt(4);
                float cena = resultSet.getFloat(5);
                Projekcija p = new Projekcija(id, filmId, datum_vreme, karte, cena);
                projekcijas.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Projekcija> getProjekcijas() {
        return projekcijas;
    }

    public void refreshKartas() {
        Controller.getInstance().getRezervacijeFrame().getKarteTableView().getItems().clear();
        kartas.clear();
        getAllRezervacije();
        Controller.getInstance().getRezervacijeFrame().getKarteTableView().setItems(kartas);

    }

    public void updateKarta() {
        query = "UPDATE `karta` SET `projekcija_id` = ? ,`username` = ? ,`red` =?,`sediste`=?,`popust`=?, `cena`=? WHERE `karta`.`karta_id` = ?";

        try {

            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getProjekcijaIdField().getText()));
            ps.setString(2, Controller.getInstance().getRezervacijeFrame().getUsernameField().getText());
            ps.setInt(3, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getRedField().getText()));
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getSedisteField().getText()));
            ps.setString(5, Controller.getInstance().getRezervacijeFrame().getPopustField().getText());
            ps.setFloat(6, Float.parseFloat(Controller.getInstance().getRezervacijeFrame().getCenaField().getText()));
            ps.setInt(7, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getIdField().getText()));
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addKarta() {
        query = "INSERT INTO `karta` (`projekcija_id` ,`username` ,`popust`,`red` ,`sediste`, `cena`)"
                + " VALUES (?, ?, ?,?, ?,?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getProjekcijaIdField().getText()));
            ps.setString(2, Controller.getInstance().getRezervacijeFrame().getUsernameField().getText());
            ps.setString(3, Controller.getInstance().getRezervacijeFrame().getPopustField().getText());
            ps.setInt(4, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getRedField().getText()));
            ps.setInt(5, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getSedisteField().getText()));
            ps.setFloat(6, Float.parseFloat(Controller.getInstance().getRezervacijeFrame().getCenaField().getText()));

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addKarta(int projekcija, String username, String popust, int red, int sediste, float cena) {
        query = "INSERT INTO `karta` (`projekcija_id` ,`username`, `popust`,`red` ,`sediste`, `cena`)"
                + " VALUES (?, ?, ?,?, ?,?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setInt(1, projekcija);
            ps.setString(2, username);
            ps.setString(3, popust);
            ps.setInt(4, red);
            ps.setInt(5, sediste);
            ps.setFloat(6, cena);
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteKarta() {
        query = "DELETE FROM `karta` WHERE `karta`.`karta_id` =?";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(Controller.getInstance().getRezervacijeFrame().getIdField().getText()));
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllRezervacije() {
        kartas = FXCollections.observableArrayList();
        query = "SELECT * FROM `karta`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int projekcija_id = resultSet.getInt(2);
                String username = resultSet.getString(3);
                String popust = resultSet.getString(4);
                int red = resultSet.getInt(5);
                int sediste = resultSet.getInt(6);
                float cena = resultSet.getFloat(7);

                Karta k = new Karta(id, projekcija_id, username, popust, red, sediste, cena);
                kartas.add(k);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Karta> getRezervacijes() {
        return kartas;
    }

    public void singUp(String user, String pass, String ime, String prezime, String email, String kontakt) {
        query = "INSERT INTO `korisnik` (`username`, `password`, `status_id`, `ime`, `prezime`, `email`, `kontakt`)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setInt(3, 1);
            ps.setString(4, ime);
            ps.setString(5, prezime);
            ps.setString(6, email);
            ps.setString(7, kontakt);

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getMovies(String value) {
        movies = FXCollections.observableArrayList();
        query = "SELECT * FROM `film`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);
                String zanr = resultSet.getString(3);
                int godina = resultSet.getInt(4);
                String opis = resultSet.getString(5);
                String glumci = resultSet.getString(6);
                String trajanje = resultSet.getString(7);
                if (zanr.equalsIgnoreCase(value)) {
                    Film movie = new Film(id, naziv, zanr, godina, opis, glumci, trajanje);

                    movies.add(movie);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getProjekcija(Film rowData) {
        projekcijaP = FXCollections.observableArrayList();
        query = "SELECT * FROM `projekcija`";
        Statement statement;

        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                int filmId = resultSet.getInt(2);
                String datum_vreme = resultSet.getString(3);
                int karte = resultSet.getInt(4);
                float cena = resultSet.getFloat(5);
                if (filmId == rowData.getId()) {
                    Projekcija p = new Projekcija(id, filmId, datum_vreme, karte, cena);
                    projekcijaP.add(p);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Projekcija> getProjekcijaP() {
        return projekcijaP;
    }

    public void setProjekcijaP(ObservableList<Projekcija> projekcijaP) {
        this.projekcijaP = projekcijaP;
    }

    public void getAllRezervacije(int idP) {
        kartas.clear();
        OdabirMestaFrame.getRezervisane().clear();
        query = "SELECT * FROM `karta`";
        Statement statement;
        try {
            statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int projekcija_id = resultSet.getInt(2);
                String username = resultSet.getString(3);
                String popust = resultSet.getString(4);
                int red = resultSet.getInt(5);
                int sediste = resultSet.getInt(6);
                float cena = resultSet.getFloat(8);
                if (projekcija_id == idP) {
                    String str = red + "," + sediste;
                    OdabirMestaFrame.getRezervisane().add(str);
                }
                Karta k = new Karta(id, projekcija_id, username, popust, red, sediste, cena);
                kartas.add(k);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
