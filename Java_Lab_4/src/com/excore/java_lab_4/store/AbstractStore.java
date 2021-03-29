package com.excore.java_lab_4.store;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractStore<T> implements Serializable, Iterable<T> {
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
//        for (int i = 0; i < count; i++) {
//            sb.append(arr[i]).append('\n');
//        }
        for (T item : this) {
            sb.append(item).append('\n');
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new StoreIterator();
    }

    private class StoreIterator implements Iterator<T> {
        int current = 0;
        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public void remove() {
            System.arraycopy(arr, current, arr, current - 1, count - current);
            count--;
            current--;
        }

        @Override
        public T next() {
            return (T) arr[current++];
        }
    }
}
