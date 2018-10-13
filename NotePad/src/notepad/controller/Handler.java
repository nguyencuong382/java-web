/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad.controller;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Stack;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import notepad.share.Util;
import notepad.view.NotePadForm;

/**
 *
 * @author Admin
 */
public abstract class Handler implements ActionListener {

    public static NotePadForm notePad;
    public static JTextArea txtArea;
    public static String pathToFile = null;
    public static boolean hasTextChange = false;

    public Handler(NotePadForm notePad) {
        Handler.notePad = notePad;
        txtArea = notePad.getTxtArea();
        previousText = txtArea.getText();
    }

    public void addListener(JMenu menu) {
        Component components[] = menu.getMenuComponents();
        for (Component component : components) {
            try {
                JMenuItem btn = (JMenuItem) component;
                btn.addActionListener(this);
            } catch (Exception e) {
            }
        }
    }

    static String previousText;
    static Stack<Cache> memoryUndo = new Stack<>();
    static Stack<Cache> memoryRedo = new Stack<>();
    static boolean shouldUpdateMemory = true;

    public void reset() {
        shouldUpdateMemory = false;
        txtArea.setText("");
        notePad.setTitle(Util.nameNewFile);
        memoryUndo.clear();
        memoryRedo.clear();
        shouldUpdateMemory = true;
        hasTextChange = false;
        pathToFile = null;
    }

    public void init() {
        notePad.setTitle(Util.nameNewFile);
        // load font from data store
        FileInputStream fi;
        try {
            fi = new FileInputStream(new File("data.bat"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            DataStore data = (DataStore) oi.readObject();
            txtArea.setFont(data.getFont());
        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        this.addListenerToTextArea();
    }

    public void addListenerToTextArea() {
        txtArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // what ever user typed on text is change, the file need to save if a change occurs
                shouldUpdateMemory = true;
            }
        });
        txtArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    setStateMemory();
                } catch (Exception ex) {
                }
            }
        });
        txtArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // undo and redo action make event on text area
                // so when undo, for example if undo insert 'a' character,
                // it also call remove update event
                // the event will be pushed in to undo stack
                // it mean that user must be undo twice to continue undo the next last state
                if (shouldUpdateMemory) {
                    try {
                        // add state change to the stack
                        memoryUndo.push(new Cache(
                                e.getOffset(),
                                e.getLength(),
                                e.getDocument().getText(e.getOffset(), e.getLength()),
                                "I")
                        );
                        memoryRedo.clear();
                    } catch (BadLocationException ex) {
                        System.out.println(ex);
                    }
                }
                previousText = txtArea.getText();
                hasTextChange = true;
                setStateBtns();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (shouldUpdateMemory) {
                    memoryUndo.push(new Cache(
                            e.getOffset(),
                            e.getLength(),
                            previousText.substring(e.getOffset(), e.getOffset() + e.getLength()),
                            "D")
                    );
                    memoryRedo.clear();
                }
                hasTextChange = true;
                setStateBtns();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("changed");
            }
        }
        );
    }

    public void setStateBtns() {
        notePad.getBtnRedo().setEnabled(!memoryRedo.empty());
        notePad.getBtnUndo().setEnabled(!memoryUndo.empty());
        notePad.getBtnFind().setEnabled(!Util.isNothing());
        notePad.getBtnReplace().setEnabled(!Util.isNothing());

    }

    public static void setStateMemory() {
        String textSelected = txtArea.getSelectedText();
        boolean enable = textSelected != null;
        notePad.getBtnCut().setEnabled(enable);
        notePad.getBtnCopy().setEnabled(enable);
    }
}
