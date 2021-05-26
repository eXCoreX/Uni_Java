package com.excore.java_lab_8.test;

import com.excore.java_lab_8.model.*;
import com.excore.java_lab_8.store.ProductStore;
import com.excore.java_lab_8.store.WoodDirectory;
import com.excore.java_lab_8.threads.CylinderShop;
import com.excore.java_lab_8.threads.TimberShop;
import com.excore.java_lab_8.threads.WoodShop;

import java.util.ListIterator;

public class TestApp {
    private final WoodDirectory wd = new WoodDirectory();
    private final ProductStore ps = new ProductStore();

    public TestApp() {
        WoodShop timberShop = new TimberShop("timberShop", wd, ps, 1488/2);
        WoodShop cylinderShop = new CylinderShop("cylinderShop", wd, ps, 1488/2);
        Thread t1 = new Thread(timberShop);
        Thread t2 = new Thread(cylinderShop);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListIterator<INamedProduct> it = ps.listIterator();
        int i = 0;
        while (it.hasNext()) {
            it.next();
            if (i >= 1487) {
                it.remove();
            }
            else {
                i++;
            }
        }
    }

    public ProductStore getProductStore()
    {
        return ps;
    }

    private float calcWeight() {
        float result = 0f;
        for (IWeight t :
                ps.getArr()) {
            result += t.getWeight();
        }
        return result;
    }

    public void startApp() {

    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
