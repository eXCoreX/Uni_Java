package com.excore.java_lab_4.store;

import com.excore.java_lab_4.model.IWeight;

import java.util.Arrays;

public class ProductStore extends AbstractStore<IWeight> {
    public IWeight[] getArr() {
        return Arrays.copyOf(arr, count, IWeight[].class);
    }

    @Override
    public IWeight get(int idx) {
        return super.get(idx);
    }

    @Override
    public void add(IWeight newItem) {
        super.add(newItem);
    }

    @Override
    public String toString() {
        return "Перелік виробів:\n" + super.toString();
    }
}
