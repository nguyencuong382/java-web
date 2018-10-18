
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class TestSolvable {

    List<Integer> arr_ = new ArrayList<>();
    int size = 4;
    
    public TestSolvable() {
//        arr_.add(6);
//        arr_.add(13);
//        arr_.add(7);
//        arr_.add(10);
//
//        arr_.add(8);
//        arr_.add(9);
//        arr_.add(11);
//        arr_.add(-1);
//
//        arr_.add(15);
//        arr_.add(2);
//        arr_.add(12);
//        arr_.add(5);
//
//        arr_.add(14);
//        arr_.add(3);
//        arr_.add(1);
//        arr_.add(4);
        
        
        arr_.add(3);
        arr_.add(9);
        arr_.add(1);
        arr_.add(15);

        arr_.add(14);
        arr_.add(11);
        arr_.add(4);
        arr_.add(6);

        arr_.add(13);
        arr_.add(-1);
        arr_.add(10);
        arr_.add(12);

        arr_.add(2);
        arr_.add(7);
        arr_.add(8);
        arr_.add(4);
    }

    public int getInversion() {
        int count = 0;
        for (int i = 0; i < arr_.size(); i++) {
            int value_i = arr_.get(i);
            if (value_i == -1) {
                continue;
            }

            for (int j = i + 1; j < arr_.size(); j++) {
                int value_j = arr_.get(j);
                if (value_j == -1) {
                    continue;
                }

                if (value_i > value_j) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getEmptyPostion() {
        for (int i = 0; i < size * size; i++) {
            if (arr_.get(i) == -1) {
                return size - (i / size);
            }
        }

        return -1;
    }

    public boolean solvable() {

        int inversion = getInversion();

        if (size % 2 == 1) {
            if (inversion % 2 == 0) {
                return true;
            }
        } else {
            int pos = getEmptyPostion();

            if (pos % 2 == 0 && inversion % 2 == 1) {
                return true;
            }

            if (pos % 2 == 1 && inversion % 2 == 0) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new TestSolvable().solvable());
    }
}
