package com.excore.java_lab_8.threads;

import com.excore.java_lab_8.model.INamedProduct;
import com.excore.java_lab_8.model.IWeight;
import com.excore.java_lab_8.model.Waste;
import com.excore.java_lab_8.store.ProductStore;
import com.excore.java_lab_8.store.WoodDirectory;

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
                INamedProduct product = createProduct();
                ps.add(product);
                System.out.println(this.getName() + " created " + product);
                if (rnd.nextFloat() <= 0.1) {
                    ps.add(new Waste(rnd.nextFloat() / 10));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract INamedProduct createProduct() throws Exception;

    private String getName() {
        return name;
    }
}
