package com.excore.java_lab_1.store;

import com.excore.java_lab_1.model.Timber;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductStore {
    private int count = 0;
    private Timber[] arr = new Timber[3];

    public Timber[] getArr() {
        return Arrays.copyOf(arr, count);
    }

    public Timber get(int idx) {
        if (idx >= 0 && idx < count) {
            return arr[idx];
        }
        return null; // Out of range
    }

    public void add(Timber newTimber) {
        if (arr.length == count) {
            arr = Arrays.copyOf(arr, count + count / 2);
        }
        arr[count++] = newTimber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Перелік брусів:\n");

        for (int i = 0; i < count; i++) {
            sb.append(arr[i]).append('\n');
        }
        return sb.toString();
    }
}
