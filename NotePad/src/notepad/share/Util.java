/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad.share;

import notepad.controller.Handler;

/**
 *
 * @author Admin
 */
public class Util {
    public static String nameNewFile = "Untitled";
    
    public static String formatNameButton(String btnName){
        btnName = btnName.replaceAll("\\s|\\.", "");
        return btnName;
    }
    
    public static boolean isNewFile() {
        return Handler.notePad.getTitle().equals("Untitled");
    }
    
    public static boolean isNothing() {
        return Handler.txtArea.getDocument().getLength() == 0;
    }
    
    public static boolean needToSaveFile() {
        if(isNewFile()){
            if(!isNothing()){
                return true;
            }
        } else {
            if(Handler.hasTextChange){
                return true;
            }
        }
        
        return false;
    }
}
