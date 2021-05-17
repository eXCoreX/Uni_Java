package com.excore.java_lab_7.threads;

import com.excore.java_lab_7.model.IWeight;
import com.excore.java_lab_7.model.Timber;
import com.excore.java_lab_7.model.Wood;
import com.excore.java_lab_7.store.ProductStore;
import com.excore.java_lab_7.store.WoodDirectory;

import java.util.Random;

public class TimberShop extends Thread {
    WoodDirectory wd;
    ProductStore ps;
    Random rnd = new Random();
    int n;

    public TimberShop(WoodDirectory wd, ProductStore ps, int n) {
        this.wd = wd;
        this.ps = ps;
        this.n = n;
    }

    IWeight createProduct() throws Exception {
        int id = rnd.nextInt(3) + 1;
        Wood wood = wd.get(id);
        float length = 1 + rnd.nextFloat() * 10;
        float width = 0.1f + rnd.nextFloat();
        float height = 0.1f + rnd.nextFloat();

        Timber timber = new Timber(wood, length, height, width);
        return timber;
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(rnd.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                IWeight timber = createProduct();
                System.out.println(this.getName() + " created " + timber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
