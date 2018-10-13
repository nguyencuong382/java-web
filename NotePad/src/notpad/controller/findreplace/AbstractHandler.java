/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notpad.controller.findreplace;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import static notepad.controller.Handler.notePad;
import static notepad.controller.Handler.txtArea;

/**
 *
 * @author Admin
 */
public abstract class AbstractHandler {

    IViewFind view;
    JTextArea txtArea;
    JTextField txtFind;
    JCheckBox btnMathCase;
    JButton btnFind;
    static MouseAdapter adapter;
    boolean found = false;
    String previous_serch = "";
    int pos, lastPos, findLength;

    public AbstractHandler(IViewFind view) {
        this.view = view;
        this.txtArea = view.getArea();
        this.txtFind = view.getTxtFind();
        this.btnMathCase = view.getBtnMatchCase();
        this.btnFind = view.getBtnFind();

        pos = txtArea.getCaretPosition();
        lastPos = pos;
    }

    public void init() {
        if (adapter == null) {
            adapter = new MouseAdapter() {

                // user may do some event when finding or replacing
                // this effects the position of caret to finding
                @Override
                public void mouseClicked(MouseEvent e) {
                    previous_serch = "";
                    pos = txtArea.getCaretPosition();
                    lastPos = pos;
                    found = false;
                }
            };
            txtArea.addMouseListener(adapter);
        }

        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnFindAction();
            }
        });

        txtFind.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                txtFindWhatKeyReleased();
            }

        });
    }

    /**
     * Disable find button when nothing in the searching text field
     */
    public void txtFindWhatKeyReleased() {
        if (this.txtFind.getText().equals("")) {
            this.btnFind.setEnabled(false);
        } else {
            this.btnFind.setEnabled(true);
        }
    }

    /**
     * check if two words is equal and satisfies condition like matching case
     *
     * @param str_1
     * @param str_2
     * @return
     */
    public boolean match(String str_1, String str_2) {
        if (this.btnMathCase.isSelected()) {
            if (str_1.equals(str_2)) {
                return true;
            }
        } else {
            if (str_1.equalsIgnoreCase(str_2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find word downing direction
     *
     * @param findText
     * @return
     * @throws BadLocationException
     */
    public boolean findDown(String findText) throws BadLocationException {
        Document document = txtArea.getDocument();
        findLength = findText.length();
        boolean found_ = false;

        // loop every string in the text which has length equal serching text length 
        // and loop from left to right
        // For example: "123abc", serching text length is 2, the loop will browse 12 then 3a then bc
        while (pos + findLength <= document.getLength()) {
            // get match text each loop
            String matchText = document.getText(pos, findLength);

            // check if match text is equal searching text
            if (match(matchText, findText)) {
                found_ = true;
                lastPos = pos;
                break;
            }
            pos++;
        }
        if (found_) {
            // display found text with background
            this.setCaret(pos, findLength);

            // go to end postion of found tet to find next text
            pos += findLength;
        } else {
            pos = lastPos + findLength;
            JOptionPane.showMessageDialog((JDialog) view, "Can't find " + findText);
        }

        found = found_;
        return found;
    }

    /**
     * Find word up direction
     *
     * @param findText
     * @return
     * @throws BadLocationException
     */
    public boolean findUp(String findText) throws BadLocationException {
        Document document = txtArea.getDocument();
        findLength = findText.length();
        boolean found_ = false;

        // loop every string in the text which has length equal serching text length 
        // and loop from right to left
        // For example: "123abc", serching text length is 2, the loop will browse bc then 3a then 12
        while (pos - findLength >= 0) {
            String matchText = document.getText(pos - findLength, findLength);
            if (match(matchText, findText)) {
                found_ = true;
                lastPos = pos;
                break;
            }
            pos--;
        }

        if (found_) {
            this.setCaret(pos - findLength, findLength);
            pos -= findLength;
        } else {
            pos = lastPos - findLength;
            JOptionPane.showMessageDialog((JDialog) view, "Can't find " + findText);
        }
        found = found_;
        return found_;
    }

    /**
     * Display caret (the word will be filled with background color)
     *
     * @param pos
     * @param length
     * @throws BadLocationException
     */
    public void setCaret(int pos, int length) throws BadLocationException {
        Rectangle viewRect = txtArea.modelToView(pos);
        txtArea.scrollRectToVisible(viewRect);
        txtArea.setCaretPosition(pos + length);
        txtArea.moveCaretPosition(pos);

       
    }

    public void removeCaret() {
        txtArea.setCaretPosition(txtArea.getCaretPosition());
    }

    public abstract void btnFindAction();
}
