/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notpad.controller.findreplace;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public interface IViewFind {
    JTextArea getArea();
    JButton getBtnFind();
    JTextField getTxtFind();
    JCheckBox getBtnMatchCase();
}
