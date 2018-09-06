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
public class Film {

    private int id;
    private String naziv;
    private String zanr;
    private int godina;
    private String opis;
    private String glumci;
    private String vremeTrajanja;

    public Film(int id, String naziv, String zanr, int godina, String opis, String glumci, String vremeTrajanja) {
        this.id = id;
        this.naziv = naziv;
        this.zanr = zanr;
        this.godina = godina;
        this.opis = opis;
        this.glumci = glumci;
        this.vremeTrajanja = vremeTrajanja;
    }

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getGlumci() {
        return glumci;
    }

    public String getVremeTrajanja() {
        return vremeTrajanja;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    
    public String print() {
        return "Naziv: " + naziv + "\n-------------------------------------------------------------\n"
                + "Zanr: " + zanr + "\n-------------------------------------------------------------\n"
                + "Godina: " + godina + "\n-------------------------------------------------------------\n"
                + "Opis: " + "\n" + opis + "\n-------------------------------------------------------------\n"
                + "Glumci: " + glumci + "\n-------------------------------------------------------------\n"
                + "Trajanje: " + vremeTrajanja;
    }

}
