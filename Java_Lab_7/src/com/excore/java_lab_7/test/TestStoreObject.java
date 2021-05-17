package com.excore.java_lab_7.test;

import com.excore.java_lab_7.model.Wood;
import com.excore.java_lab_7.store.WoodDirectory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TestStoreObject {
    public static void main(String[] args) {
        WoodDirectory wd = new WoodDirectory();
        wd.addChecked(new Wood(10, "Aloha", 1.1f));

        File f = new File("wd.object");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(wd);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
