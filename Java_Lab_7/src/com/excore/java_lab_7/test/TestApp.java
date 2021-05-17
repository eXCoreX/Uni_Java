package com.excore.java_lab_7.test;

import com.excore.java_lab_7.model.*;
import com.excore.java_lab_7.store.ProductStore;
import com.excore.java_lab_7.store.WoodDirectory;
import com.excore.java_lab_7.threads.CylinderShop;
import com.excore.java_lab_7.threads.TimberShop;
import com.excore.java_lab_7.threads.WoodShop;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Predicate;

public class TestApp {
    private final WoodDirectory wd = new WoodDirectory();
    private final ProductStore ps = new ProductStore();

    private float calcWeight() {
        float result = 0f;
        for (IWeight t :
                ps.getArr()) {
            result += t.weight();
        }
        return result;
    }

    private void startApp() {
        WoodShop timberShop = new TimberShop("timberShop", wd, ps, 100);
        WoodShop cylinderShop = new CylinderShop("cylinderShop", wd, ps, 100);
        Thread t1 = new Thread(timberShop);
        Thread t2 = new Thread(cylinderShop);
        t1.start();
        t2.start();
        (new Thread(() -> {
            try {
                t1.join();
                t2.join();
                System.out.println(ps.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
