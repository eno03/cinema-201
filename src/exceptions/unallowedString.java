/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author wint4
 */
public class unallowedString extends Exception {

    public unallowedString(String poruka) {
        super(poruka);
    }

}
