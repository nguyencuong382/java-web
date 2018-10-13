/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Handler {

    PuzzleView view;
    JComboBox<String> listSize;
    JPanel pnBtn;
    JLabel txtSec;
    JLabel txtMoveCount;
    int size = 3;
    List<Integer> arr_;
    int count = 0;
    CountTime countTimeThread;

    public Handler(PuzzleView view) {
        this.view = view;

        listSize = view.getListSize();
        pnBtn = view.getPnBtn();
        txtMoveCount = view.getTxtMoveCount();
        txtSec = view.getTxtSec();

        this.addListener();

        countTimeThread = new CountTime();
        countTimeThread.start();
    }

    class CountTime extends Thread {

        int countTime = 0;
        boolean stop = true;

        @Override
        public void run() {
            while (true) {
                try {
                    if (!stop) {
                        txtSec.setText(++countTime + " sec");
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }

            }
        }

        public void stop_() {
            countTime = 0;
            txtSec.setText(countTime + " sec");
            stop = true;
        }

        public void pause() {
            stop = true;
        }

        public void start_() {
            stop = false;
        }
    }

    public void addListener() {
        listSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                int new_size = Integer.parseInt(((String) listSize.getSelectedItem()).charAt(0) + "");
                if (size != new_size) {
                    size = new_size;
                }
            }
        });

        view.getBtnNewGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!playing) {
                    gen();
                    countTimeThread.start_();
                    playing = true;
                } else {
                    countTimeThread.pause();
                    Object[] options = {"Yes",
                        "No"};
                    int n = JOptionPane.showOptionDialog(view,
                            "Do you want continue game?",
                            "A Silly Question",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, //do not use a custom Icon
                            options, //the titles of buttons
                            options[0]); //default button title

                    if (n == 0) {
                        countTimeThread.start_();
                    } else {
                        countTimeThread.stop_();
                        gen();
                        countTimeThread.start_();
                        count = 0;
                        txtMoveCount.setText(count + "");
                    }
                }
            }
        });
    }

    boolean playing = false;

    public void gen() {
        arr_ = new ArrayList<>();
        for (int i = 0; i < size * size - 1; i++) {
            arr_.add(i + 1);
        }
        arr_.add(-1);
        Collections.shuffle(arr_);
        
        if(checkWin()){
            gen();
            return ;
        }

        pnBtn.removeAll();
        pnBtn.setLayout(new java.awt.GridLayout(0, size, 5, 5));
        for (int i = 0; i < size * size; i++) {
            int label = arr_.get(i);
            String labelString = label + "";

            if (label == -1) {
                labelString = "";
            }
            JButton btn = new javax.swing.JButton(labelString);
            btn.setMinimumSize(new java.awt.Dimension(50, 50));
            btn.setPreferredSize(new java.awt.Dimension(50, 50));

            btn.setActionCommand(i + "");

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (playing) {
                        btnNumberAction(Integer.parseInt(e.getActionCommand()));
                    }
                }
            });

            pnBtn.add(btn);
        }

        view.pack();
    }

    public void btnNumberAction(int pos) {
        int posGap = get(pos);
        if (posGap == -1) {
            return;
        }
        swap(pos, posGap);
        txtMoveCount.setText(++count + "");

        if (checkWin()) {
            countTimeThread.pause();
            Object[] options = {"Yes",
                "Exit Game"};
            int n = JOptionPane.showOptionDialog(view,
                    "You won! Do you want to start a new game?",
                    "A Silly Question",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[0]); //default button title

            if (n == 0) {
                countTimeThread.stop_();
                count = 0;
                txtMoveCount.setText(count + "");
                playing = false;
            } else {
                System.exit(0);
            }
        }
    }

    public void swap(int pos, int posGap) {
        arr_.set(posGap, arr_.get(pos));
        arr_.set(pos, -1);

        JButton btn = (JButton) pnBtn.getComponent(pos);
        String labelString = btn.getText();
        ((JButton) pnBtn.getComponent(posGap)).setText(labelString);
        btn.setText("");
    }

    public boolean checkWin() {
        for (int i = 0; i < size * size - 1; i++) {
            if (arr_.get(i) != i + 1) {
                return false;
            }
        }
        return true;
    }

    public int get(int pos) {
        int row = pos / size;
        int col = pos % size;
        // row up
        int row_up = (row - 1) * size + col;
        // row down
        int row_down = (row + 1) * size + col;

        // col left
        int col_left = row * size + col - 1;
        // col right
        int col_right = row * size + col + 1;

//        System.out.println(arr_.get(pos));
        if (row - 1 >= 0 && arr_.get(row_up) == -1) {
            return row_up;
        }

        if (row + 1 < size && arr_.get(row_down) == -1) {
            return row_down;
        }

        if (col - 1 >= 0 && arr_.get(col_left) == -1) {
            return col_left;
        }

        if (col + 1 < size && arr_.get(col_right) == -1) {
            return col_right;
        }

        return -1;
    }

    public void show() {
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(arr_.get(k++) + " ");
            }
            System.out.println("");
        }
    }
}
