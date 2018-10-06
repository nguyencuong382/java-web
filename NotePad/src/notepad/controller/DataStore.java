/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad.controller;

import java.awt.Font;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class DataStore implements Serializable{
    private Font font;

    public DataStore() {
    }

    public DataStore(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    
    
}
