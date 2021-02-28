package com.excore.java_lab_2.store;

import com.excore.java_lab_2.model.AbstractForm;
import com.excore.java_lab_2.model.Timber;

import java.util.Arrays;

public class ProductStore {
    private int count = 0;
    private AbstractForm[] arr = new AbstractForm[3];

    public AbstractForm[] getArr() {
        return Arrays.copyOf(arr, count);
    }

    public AbstractForm get(int idx) {
        if (idx >= 0 && idx < count) {
            return arr[idx];
        }
        return null; // Out of range
    }

    public void add(AbstractForm newProduct) {
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
