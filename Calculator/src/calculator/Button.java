/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public abstract class Button implements ActionListener {
    Calculator mainCal;

    public Button(Calculator mainCal) {
        this.mainCal = mainCal;
    }
    
    public void addListener(ButtonGroup buttonGroup) {
        Enumeration buttons = buttonGroup.getElements();
        while (buttons.hasMoreElements()) {
            JButton btn = (JButton) buttons.nextElement();
            btn.addActionListener(this);
//            btn.setBorder( new TextBubbleBorder(Color.yellow, 3, 20));
        }
    }
    
    public abstract void reset();
}
