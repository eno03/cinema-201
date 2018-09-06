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
public class Karta {

    private int id;
    private int projekcija_id;
    private String username;
    private int red;
    private int sediste;
    private String popust;
    private float cena;

    public Karta(int id, int projekcija_id, String username, String popust, int red, int sediste, float cena) {
        this.id = id;
        this.projekcija_id = projekcija_id;
        this.username = username;
        this.red = red;
        this.sediste = sediste;
        this.popust = popust;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public int getProjekcija_id() {
        return projekcija_id;
    }

    public String getUsername() {
        return username;
    }

    public int getSediste() {
        return sediste;
    }

    public int getRed() {
        return red;
    }

    public String getPopust() {
        return popust;
    }

    public float getCena() {
        return cena;
    }

}
