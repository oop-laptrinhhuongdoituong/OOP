package com.example.baitaplonoop;

import java.util.ArrayList;
import java.util.Collections;

public class test {
    public static void main(String[] args) {

    }

    public static class Test {
        public static void main(String[] args) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            for(int i = 0; i < 4; i++){
                System.out.println(list.get(i));
            }
            System.out.println();

            Collections.shuffle(list);


            for(int i = 0; i < 4; i++){
                System.out.println(list.get(i));
            }
        }
    }
}