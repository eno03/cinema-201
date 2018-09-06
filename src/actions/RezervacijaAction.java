/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions;

import controller.Connector;
import controller.Controller;
import exceptions.emptyString;
import exceptions.unallowedString;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javax.swing.JOptionPane;

/**
 *
 * @author nikol
 */
public class RezervacijaAction implements EventHandler<ActionEvent> {
    
    public RezervacijaAction() {
    }
    
    @Override
    public void handle(ActionEvent event) {
        
        int projekcija = Controller.getInstance().getAccountAFrame().getIzabranaProjekcija().getId();
        String user = Controller.getInstance().getAccountAFrame().getUserField().getText();
        String[] red = Controller.getInstance().getAccountAFrame().getRedField().getText().split(",");
        String[] sediste = Controller.getInstance().getAccountAFrame().getSedisteField().getText().split(",");
        String ime = Controller.getInstance().getAccountAFrame().getImeField().getText();
        String prezime = Controller.getInstance().getAccountAFrame().getPrezimeField().getText();
        String popust = Controller.getInstance().getAccountAFrame().getDiscount_group().getSelectedToggle().getUserData().toString().trim();
        float cena = Float.parseFloat(Controller.getInstance().getAccountAFrame().getCenaField().getText());
        try {
            checkI(ime, prezime);
            for (int i = 0; i < red.length; i++) {
                Connector.getInstance().addKarta(projekcija, user, popust, Integer.parseInt(red[i]), Integer.parseInt(sediste[i]),cena);
            }
            
            JOptionPane.showMessageDialog(null, "Rezervacija je uspesno poslata! Ocekujte poziv operatera...");
        } catch (emptyString | unallowedString ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    
    private void checkI(String ime, String prezime) throws emptyString, unallowedString {
        
        if (ime.isEmpty()) {
            throw new emptyString("Unesite ime!");
        } else if (prezime.isEmpty()) {
            throw new emptyString("Unesite prezime!");
        }
        
        if (!ime.matches("[a-zA-Z]+")) {
            throw new unallowedString("Ime ne sme sadrzati nista osim slova!");
        }
        if (!prezime.matches("[a-zA-Z]+")) {
            throw new unallowedString("Prezime ne sme sadrzati nista osim slova!");
        }
    }
    
}
