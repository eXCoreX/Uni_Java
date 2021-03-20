package com.excore.java_lab_3_2.store;

import java.io.Serializable;
import java.util.Arrays;

public abstract class AbstractStore<T> implements Serializable {
    protected int count = 0;
    protected Object[] arr = new Object[3];

    public int getCount() {
        return count;
    }

    public abstract T[] getArr();

    protected T get(int idx) {
        if (idx >= 0 && idx < count) {
            return (T)arr[idx];
        }
        return null; // Out of range
    }

    protected void add(T newItem) {
        if (arr.length == count) {
            arr = Arrays.copyOf(arr, count + count / 2 + 1);
        }
        arr[count++] = newItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(arr[i]).append('\n');
        }
        return sb.toString();
    }
}
