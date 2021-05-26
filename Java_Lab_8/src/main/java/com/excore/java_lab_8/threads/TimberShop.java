package com.excore.java_lab_8.threads;

import com.excore.java_lab_8.model.INamedProduct;
import com.excore.java_lab_8.model.IWeight;
import com.excore.java_lab_8.model.Timber;
import com.excore.java_lab_8.model.Wood;
import com.excore.java_lab_8.store.ProductStore;
import com.excore.java_lab_8.store.WoodDirectory;

public class TimberShop extends WoodShop {

    public TimberShop(String name, WoodDirectory wd, ProductStore ps, int n) {
        super(name, wd, ps, n);
    }

    public INamedProduct createProduct() throws Exception {
        int id = rnd.nextInt(3) + 1;
        Wood wood = wd.get(id);
        float length = 1 + rnd.nextFloat() * 5;
        float width = 0.1f + rnd.nextFloat();
        float height = 0.1f + rnd.nextFloat();

        Timber timber = new Timber(wood, length, height, width);
        return timber;
    }

}
