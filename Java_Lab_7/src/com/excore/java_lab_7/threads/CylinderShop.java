package com.excore.java_lab_7.threads;

import com.excore.java_lab_7.model.Cylinder;
import com.excore.java_lab_7.model.IWeight;
import com.excore.java_lab_7.model.Timber;
import com.excore.java_lab_7.model.Wood;
import com.excore.java_lab_7.store.ProductStore;
import com.excore.java_lab_7.store.WoodDirectory;

public class CylinderShop extends WoodShop {

    public CylinderShop(String name, WoodDirectory wd, ProductStore ps, int n) {
        super(name, wd, ps, n);
    }

    @Override
    protected IWeight createProduct() throws Exception {
        int id = rnd.nextInt(3) + 1;
        Wood wood = wd.get(id);
        float length = 1 + rnd.nextFloat() * 5;
        float diameter = 0.1f + rnd.nextFloat();

        Cylinder cylinder = new Cylinder(wood, length, diameter);
        return cylinder;
    }
}
