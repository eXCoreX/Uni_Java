package com.excore.java_lab_7.threads;

import com.excore.java_lab_7.model.IWeight;
import com.excore.java_lab_7.store.ProductStore;
import com.excore.java_lab_7.store.WoodDirectory;

import java.util.Random;

public abstract class WoodShop implements Runnable {
    protected final String name;
    WoodDirectory wd;
    ProductStore ps;
    Random rnd = new Random();
    int n;

    public WoodShop(String name, WoodDirectory wd, ProductStore ps, int n) {
        this.name = name;
        this.wd = wd;
        this.ps = ps;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            try {
                IWeight product = createProduct();
                ps.add(product);
                System.out.println(this.getName() + " created " + product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract IWeight createProduct() throws Exception;

    private String getName() {
        return name;
    }
}
