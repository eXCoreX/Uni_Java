package com.excore.java_lab_4.test;

import com.excore.java_lab_4.store.WoodDirectory;

import java.io.*;

public class TestRestoreObject {
    public static void main(String[] args) {
        WoodDirectory wd = null;

        File f = new File("wd.object");
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            wd = (WoodDirectory) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (wd != null) {
            System.out.println(wd);
        }
    }
}
