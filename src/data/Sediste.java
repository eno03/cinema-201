/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javafx.scene.control.Button;

/**
 *
 * @author nikol
 */
public class Sediste extends Button {

    private boolean oznaceno;

    public Sediste() {
        super();
    }

    public Sediste(String string) {
        super(string);
    }

    public boolean isOznaceno() {
        return oznaceno;
    }

    public void setOznaceno(boolean oznaceno) {
        this.oznaceno = oznaceno;
    }

}
