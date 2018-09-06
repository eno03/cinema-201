/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nikol
 */
public class AccountFrameTest {

    public AccountFrameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

   

    /**
     * Test of izracunajCenu method for empty string, of class AccountFrame.
     */
    @Test
    public void testIzracunajCenuEmpty() {
        System.out.println("izracunajCenu_EmptyString");
        //Inicijalizacija promenljivih potrebnih klasa za testiranje
        float cena = 2000.0F;
        String popust = "";

        float expResult = 2000.0F; // ocekivana cena
        float result = AccountFrame.izracunajCenu(cena, popust); // izracunata cena 
        assertEquals(expResult, result, 0.0); //  test bi trebao da prodje, sto znaci da je cena ostala ista
        System.out.println("Cena je ostala nepromenjenja.");
    }
}
