/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author nikol
 */
public class Korisnik {

    private String username;
    private String password;
    private int status;
    private String ime;
    private String prezime;
    private String email;
    private String kontaktTelefon;

    public Korisnik(String username, String password, int status, String ime, String prezime, String eMail, String kontaktTelefon) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.ime = ime;
        this.prezime = prezime;
        this.email = eMail;
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String geteMail() {
        return email;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getStatus() {
        return status;
    }

}
