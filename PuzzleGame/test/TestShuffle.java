
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class TestShuffle {

    int size = 4;
    List<Integer> arr_;

    public void gen() {
        arr_ = new ArrayList<>();
        for (int i = 0; i < size * size - 1; i++) {
            arr_.add(i + 1);
        }

        arr_.add(-1);
        Collections.shuffle(arr_);
    }

    public void swap(int pos, int posGap) {
        arr_.set(posGap, arr_.get(pos));
        arr_.set(pos, -1);
    }
    
    public boolean checkWin(){
        for(int i = 0; i  < size * size - 1; i++){
            if(arr_.get(i) == i + 1){
                return true;
            }
        }
        
        return false;
    }

    public void get(int pos) {
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

        System.out.println(arr_.get(pos));

        if (row - 1 >= 0) {
            if (arr_.get(row_up) == -1) {
                swap(pos, row_up);
                return;
            }
        }

        if (row + 1 < size) {
            if (arr_.get(row_down) == -1) {
                swap(pos, row_down);
                return;
            }
        }

        if (col - 1 >= 0) {
            if (arr_.get(col_left) == -1) {
                swap(pos, col_left);
                return;
            }
        }
        if (col + 1 < size) {
            if (arr_.get(col_right) == -1) {
                swap(pos, col_right);
                return;
            }
        }
    }

    public void show() {
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr_.get(k++) + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        TestShuffle test = new TestShuffle();
        
        test.gen();
        
        System.out.println(test.checkWin());
        
        test.show();
        System.out.println("=======");
    

    }
}
