package com.excore.java_lab_2.store;

import com.excore.java_lab_2.model.IWeight;

import java.util.Arrays;

public class ProductStore {
    private int count = 0;
    private IWeight[] arr = new IWeight[3];

    public IWeight[] getArr() {
        return Arrays.copyOf(arr, count);
    }

    public IWeight get(int idx) {
        if (idx >= 0 && idx < count) {
            return arr[idx];
        }
        return null; // Out of range
    }

    public void add(IWeight newProduct) {
        if (arr.length == count) {
            arr = Arrays.copyOf(arr, count + count / 2);
        }
        arr[count++] = newProduct;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Перелік виробів:\n");

        for (int i = 0; i < count; i++) {
            sb.append(arr[i]).append('\n');
        }
        return sb.toString();
    }
}
