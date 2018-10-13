/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notpad.controller.findreplace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.text.BadLocationException;
import notepad.controller.Handler;
import notepad.view.FindDialog;

/**
 *
 * @author Admin
 */
public class FHandler extends AbstractHandler {

    FindDialog findDialog;

    public FHandler(IViewFind view) {
        super(view);
        findDialog = (FindDialog) view;
        init();
    }

    @Override
    public void init() {
        super.init();

        findDialog.getBtnUp().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpAction();
            }
        });

        findDialog.getBtnDown().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDownAction();
            }
        });

    }

    @Override
    public void btnFindAction() {
        String find = txtFind.getText();
        this.txtArea.requestFocusInWindow();

        // if the searching word change
        // we reset pos at current postion of caret
        if (!previous_serch.equals(find)) {
            previous_serch = find;
            if (previous_serch.equalsIgnoreCase(find)) {
                if (this.btnMathCase.isSelected()) {
                    pos = txtArea.getCaretPosition();
                    lastPos = pos;
                }
            } else {
                pos = txtArea.getCaretPosition();
                lastPos = pos;
            }

        }

        try {
            if (findDialog.getBtnDown().isSelected()) {
                findDown(find);
            } else {
                findUp(find);
            }

        } catch (BadLocationException e) {
            System.out.println(e);
        }
        
        Handler.setStateMemory();
    }

    // when user want to swith down to up serach
    // the postion is at the right of last found word
    // so the next word is the last found word itself
    // we move pos to the last pos (las pos is postion at the left of word)
    boolean down = true;

    public void btnUpAction() {
        if (!down) {
            pos = lastPos;
            down = true;
        }
//        state();
    }

    public void btnDownAction() {
        if (down) {
            pos = lastPos;
            down = false;
        }
//        state();
    }

    public void state() {
        System.out.println("s---------------");
        System.out.println("pos: " + pos);
        System.out.println("lastPos: " + lastPos);
        System.out.println("Found: " + found);
        System.out.println("previous search: " + previous_serch);
        System.out.println("e---------------");
    }

}
