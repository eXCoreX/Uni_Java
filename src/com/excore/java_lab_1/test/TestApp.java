package com.excore.java_lab_1.test;

import com.excore.java_lab_1.model.Wood;
import com.excore.java_lab_1.store.WoodDirectory;

public class TestApp {
    

    public static void main(String[] args) {
        WoodDirectory wd = new WoodDirectory();
        System.out.println(wd.get(3));

        Wood w = new Wood(4, "Дуб", 1f);

        if (wd.add(w)) {
            System.out.println(w);
        }

        if (!wd.add(w)) {
            System.out.println(w + " вже існує");
        }

    }
}
