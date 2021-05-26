package com.excore.java_lab_8.store;

import com.excore.java_lab_8.model.INamedProduct;
import com.excore.java_lab_8.model.IWeight;

import java.util.Arrays;

public class ProductStore extends AbstractStore<INamedProduct> {
    public INamedProduct[] getArr() {
        return Arrays.copyOf(arr, count, INamedProduct[].class);
    }

    @Override
    public INamedProduct get(int idx) {
        return super.get(idx);
    }

    @Override
    public void add(INamedProduct newItem) {
        super.add(newItem);
    }

    @Override
    public String toString() {
        return "Перелік виробів:\n" + super.toString();
    }
}
