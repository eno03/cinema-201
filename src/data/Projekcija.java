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
public class Projekcija {

    private int id;
    private int film_id;
    private String datum;
    private int broj_karata;
    private float cena;

    public Projekcija(int id, int film_id, String datum, int broj_karata, float cena) {
        this.id = id;
        this.film_id = film_id;
        this.datum = datum;
        this.broj_karata = broj_karata;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public String getDatum() {
        return datum;
    }

    public int getBroj_karata() {
        return broj_karata;
    }

    public float getCena() {
        return cena;
    }
    
}
