/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notpad.controller.findreplace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import notepad.view.ReplaceDialog;

/**
 *
 * @author Admin
 */
public class ReplaceHandler extends AbstractHandler {

    ReplaceDialog replaceDialog;

    public ReplaceHandler(IViewFind view) {
        super(view);

        replaceDialog = (ReplaceDialog) view;
        init();
    }

    @Override
    public void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.

        replaceDialog.getBtnReplace().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReplaceAction();
            }
        });

        replaceDialog.getBtnReplaceAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReplaceAllAction();
            }
        });

        btnMathCase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnMathCaseAction();
            }
        });
    }

    @Override
    public void txtFindWhatKeyReleased() {
        super.txtFindWhatKeyReleased(); //To change body of generated methods, choose Tools | Templates.

        if (this.txtFind.getText().equals("")) {
            replaceDialog.getBtnReplace().setEnabled(false);
            replaceDialog.getBtnReplaceAll().setEnabled(false);
        } else {
            replaceDialog.getBtnReplace().setEnabled(true);
            replaceDialog.getBtnReplaceAll().setEnabled(true);
        }

    }

    @Override
    public void btnFindAction() {
        String find = txtFind.getText();
        this.txtArea.requestFocusInWindow();
        
        // if the searching word change
        // we reset pos at the start of text
        if (!previous_serch.equals(find)) {
            previous_serch = find;
            pos = 0;
            lastPos = pos;
        }

        try {
            findDown(find);
        } catch (BadLocationException e) {
            System.out.println(e);
        }
    }

    public void btnMathCaseAction() {

        try {
            String replaceText = replaceDialog.getTxtFind().getText();
            String text = txtArea.getDocument().getText(pos - findLength, findLength);
            
            // the found text may not match condition Match Case
            // so we need to check again
            if (found) {
                if (!match(text, replaceText)) {
                    found = false;
                }
            }
            // pos after all searching is at the ends of text
            // if some word(s) will be ingoreed when continue searching
            // we reset pos to the last found word
            else {
                pos = lastPos + findLength;
            }
        } catch (BadLocationException ex) {
        }
    }
    
    String previous_replace = "";

    public void btnReplaceAction() {
        String replaceText = replaceDialog.getTxtReplacewith().getText();
        
        // if the replacing word change
        // we reset state as the begining
        if (!previous_replace.equals(replaceText)) {
            previous_replace = replaceText;
            found = false;
            pos = 0;
            lastPos = 0;
            previous_serch = "";
        }

        try {
            // at the begining, not word is found
            if (!found) {
                btnFindAction();
                //return;
            }

            Document doc = this.txtArea.getDocument();

            doc.remove(pos - findLength, findLength);
            doc.insertString(pos - findLength, replaceText, null);
            this.setCaret(pos - findLength, replaceText.length());
            
            // the length of relacing word may not equal the replace word
            // so after replace, the pos must at the end of replaced word
            pos += replaceText.length() - findLength;
            
            // find next word
            btnFindAction();
        } catch (BadLocationException ex) {
            Logger.getLogger(ReplaceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnReplaceAllAction() {
        String findText = txtFind.getText();
        String replaceText = replaceDialog.getTxtReplacewith().getText();
        String currentText = txtArea.getText();

        currentText = currentText.replaceAll(findText, replaceText);

        txtArea.setText(currentText);
    }

}
