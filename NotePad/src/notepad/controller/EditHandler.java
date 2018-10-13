

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.JMenuItem;
import notepad.share.Util;
import notepad.view.ReplaceDialog;
import notepad.view.FindDialog;
import notepad.view.FontDialog;
import notepad.view.NotePadForm;

/**
 *
 * @author Admin
 */
public class EditHandler extends Handler {

    public EditHandler(NotePadForm notePad) {
        super(notePad);
        this.addListener(notePad.getBtnEdit());
    }

    public void selectAll() {
        txtArea.selectAll();
    }

    public void cut() {
        txtArea.cut();
    }

    public void copy() {
        txtArea.copy();
    }

    public void paste() {
        txtArea.paste();
    }

    public void undo() {
        if (!memoryUndo.empty()) {
            Cache cache = memoryUndo.pop();
            shouldUpdateMemory = false;
            try {
                if ("I".equals(cache.getType())) {
                    txtArea.getDocument().remove(cache.getOffset(), cache.getLength());
                } else if ("D".equals(cache.getType())) {
                    txtArea.getDocument().insertString(
                            cache.getOffset(),
                            cache.getValue(),
                            null);
                } else if ("F".equals(cache.getType())) {
                    this.changeFont(cache.getOldFont());
                }
                memoryRedo.push(cache);
            } catch (Exception e) {
                System.out.println(e);
            }
            setStateBtns();
        }
        
    }

    public void redo() {
        if (!memoryRedo.empty()) {
            Cache cache = memoryRedo.pop();
            shouldUpdateMemory = false;
            try {
                if ("D".equals(cache.getType())) {
                    txtArea.getDocument().remove(cache.getOffset(), cache.getLength());
                } else if ("I".equals(cache.getType())) {
                    txtArea.getDocument().insertString(
                            cache.getOffset(),
                            cache.getValue(),
                            null);
                } else if ("F".equals(cache.getType())) {
                    this.changeFont(cache.getNewFont());
                }
                memoryUndo.push(cache);
            } catch (Exception e) {
                System.out.println(e);
            }
            setStateBtns();
        }
    }

    public void find() {
        FindDialog find = new FindDialog(EditHandler.notePad, false);
        find.setVisible(true);
        find = null;
    }

    public void replace() {
        ReplaceDialog find = new ReplaceDialog(EditHandler.notePad, false);
        find.setVisible(true);
    }

    public void changeFont() {
        FontDialog fdl = new FontDialog(EditHandler.notePad, true);

        fdl.setVisible(true);

        Font new_f = fdl.getFont_();
        if (new_f != null) {
            try {
                Cache c = new Cache(txtArea.getFont(), new_f, "F");
                changeFont(new_f);
                memoryUndo.push(c);
            } catch (Exception ex) {
                System.out.println("Can't not change font");
            }

        }
    }

    public void changeFont(Font font) throws Exception {
        FileOutputStream f = new FileOutputStream(new File("data.bat"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(new DataStore(font));
        o.close();
        f.close();
        txtArea.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = Util.formatNameButton(((JMenuItem) e.getSource()).getText());

        switch (btnName) {
            case "SelectAll":
                this.selectAll();
                break;
            case "Cut":
                this.cut();
                break;
            case "Copy":
                this.copy();
                break;
            case "Paste":
                this.paste();
                break;
            case "Undo":
                this.undo();
                break;
            case "Redo":
                this.redo();
                break;
            case "Find":
                this.find();
                break;
            case "Replace":
                this.replace();
                break;
            case "ChangeFont":
                this.changeFont();
                break;
            default:
                break;
        }

    }

}
