/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad.controller;

import java.awt.Font;

/**
 *
 * @author Admin
 */
public class Cache {
    private int offset;
    private int length;
    private String value;
    private String type;
    private Font oldFont;
    private Font newFont;

    public Cache() {
    }

    public Cache(int offset, int length, String value, String type) {
        this.offset = offset;
        this.length = length;
        this.value = value;
        this.type = type;
    }
    
    public Cache(Font oFont, Font neFont, String type){
        this.oldFont = oFont;
        this.newFont = neFont;
        this.type = type;
    }

    public Font getOldFont() {
        return oldFont;
    }


    public Font getNewFont() {
        return newFont;
    }
    

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    @Override
    public String toString() {
        return "offset: "  + this.offset + ", length: " + this.length + ", value: " + this.value + ", " + this.type + "\n";
    }

    
    
    
}
