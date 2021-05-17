package com.excore.java_lab_7.test;

import com.excore.java_lab_7.model.*;
import com.excore.java_lab_7.store.ProductStore;
import com.excore.java_lab_7.store.WoodDirectory;
import com.excore.java_lab_7.threads.TimberShop;

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
        Thread shop1 = new TimberShop(wd, ps, 3);
        Thread shop2 = new TimberShop(wd, ps, 3);
        Thread shop3 = new TimberShop(wd, ps, 3);
        shop1.start();
        shop2.start();
        shop3.start();
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
