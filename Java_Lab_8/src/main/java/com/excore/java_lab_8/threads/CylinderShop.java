package com.excore.java_lab_8.threads;

import com.excore.java_lab_8.model.Cylinder;
import com.excore.java_lab_8.model.INamedProduct;
import com.excore.java_lab_8.model.IWeight;
import com.excore.java_lab_8.model.Wood;
import com.excore.java_lab_8.store.ProductStore;
import com.excore.java_lab_8.store.WoodDirectory;

public class CylinderShop extends WoodShop {

    public CylinderShop(String name, WoodDirectory wd, ProductStore ps, int n) {
        super(name, wd, ps, n);
    }

    @Override
    protected INamedProduct createProduct() throws Exception {
        int id = rnd.nextInt(3) + 1;
        Wood wood = wd.get(id);
        float length = 1 + rnd.nextFloat() * 5;
        float diameter = 0.1f + rnd.nextFloat();

        return new Cylinder(wood, length, diameter);
    }
}
