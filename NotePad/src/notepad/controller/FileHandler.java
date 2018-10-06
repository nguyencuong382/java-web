/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad.controller;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import static notepad.controller.Handler.pathToFile;
import notepad.share.Util;
import notepad.view.NotePadForm;

/**
 *
 * @author Admin
 */
public class FileHandler extends Handler {

    public FileHandler(NotePadForm notePad) {
        super(notePad);
        this.addListener(notePad.getBtnFile());
        
        notePad.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }

        });
    }

    boolean saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
        int userSelection = fileChooser.showSaveDialog(Handler.notePad);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            File checkFile = new File(fileToSave.getAbsolutePath());
            if (checkFile.exists()) {
                Object[] options = {"Yes",
                    "No"};
                int n = JOptionPane.showOptionDialog(notePad,
                        "The file " + fileToSave.getName() + "exist. Do you want override it?",
                        "A Silly Question",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, //do not use a custom Icon
                        options, //the titles of buttons
                        options[1]); //default button title
                if (n == 1) {
                    return saveToFile();
                }
            }

            try {
                Handler.txtArea.write(new OutputStreamWriter(new FileOutputStream(fileToSave),
                        "utf-8"));
                notePad.setTitle(fileToSave.getName());
                pathToFile = fileToSave.getAbsolutePath();
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
                return true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Handler.notePad, "Can't not save " + fileToSave.getName());
            }
        }

        return false;
    }

    boolean saveChangedFile() {
        if (pathToFile == null) {
            return saveToFile();
        }

        File file = null;
        try {
            file = new File(Handler.pathToFile);
            Handler.txtArea.write(new OutputStreamWriter(new FileOutputStream(file),
                    "utf-8"));
            System.out.println("Save as file: " + file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(Handler.notePad, "Can't not save " + file.getName());
        }

        return false;
    }

    public boolean askSaveFile() {
        Object[] options = {"Save",
            "No, don't save",
            "Cancel"};
        int n = JOptionPane.showOptionDialog(FileHandler.notePad,
                "Do you save changes to " + FileHandler.notePad.getTitle(),
                null,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 2 || n == -1) {
            return false;
        }

        if (n == 0 && !saveChangedFile()) {
            return false;
        }

        return true;
    }

    public void new_() {
        if (!Util.needToSaveFile()) {
            reset();
            return;
        }

        // save file successfully
        // user may cancel
        if (askSaveFile()) {
            reset();
        }
    }

    public void open() {
        // TODO add your handling code here:
        this.state();
        if (Util.needToSaveFile()) {
            if (!askSaveFile()) {
                return;
            }
        }
        this.state();

        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setFileFilter(new FileNameExtensionFilter("Text", "txt"));

        if (fc.showOpenDialog(FileHandler.notePad) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file)));

                reset();
                txtArea.read(input, null);
                previousText = txtArea.getText();
                pathToFile = file.getAbsolutePath();
                notePad.setTitle(file.getName());
                this.addListenerToTextArea();
            } catch (IOException e) {
                System.out.println("Can't not read " + file.getName());
            }

        }
    }

    public void save() {
        if (saveChangedFile()) {
            hasTextChange = false;
        }
    }

    public void saveAs() {
        if (saveToFile()) {
            hasTextChange = false;
        }
    }

    public void exit() {
        if (!Util.needToSaveFile()) {
            System.exit(0);
            return;
        }

        if (askSaveFile()) {
            System.exit(0);
        }

    }

    static int scope = 0;

    public void state() {
        System.out.println("state---- " + ++scope);
        System.out.println("Can save: " + Util.needToSaveFile());
        System.out.println("text changed: " + hasTextChange);
        System.out.println("Path: " + pathToFile);
        System.out.println("end state---- " + scope + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = Util.formatNameButton(((JMenuItem) e.getSource()).getText());

        switch (btnName) {
            case "Open":
                this.open();
                break;
            case "New":
                this.new_();
                break;
            case "Save":
                this.save();
                break;
            case "SaveAs":
                this.saveAs();
                break;
            case "Exit":
                this.exit();
                break;
            default:
                break;
        }
    }

}
