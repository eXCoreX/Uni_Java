package com.excore.java_lab_6.store;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

    public void remove(Predicate<T> pred) {
        Iterator<T> itr = iterator();
        while (itr.hasNext()) {
            if (pred.test(itr.next())) {
                itr.remove();
            }
        }
    }

    public void doForAll(Consumer<T> action) {
        for (T t : this) {
            action.accept(t);
        }
    }

    public void doOnlyFor(Consumer<T> action, Predicate<T> pred) {
        for (T item : this) {
            if (pred.test(item)) {
                action.accept(item);
            }
        }
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

    public ListIterator<T> listIterator() {
        return new StoreListIterator();
    }

    private class StoreIterator implements Iterator<T> {
        int cursorBefore = 0;
        int lastReturned = -1;
        boolean returnedSomething = false;
        @Override
        public boolean hasNext() {
            return cursorBefore < count;
        }

        @Override
        public void remove() {
            if (!returnedSomething) {
                throw new IllegalStateException();
            }
            System.arraycopy(arr, lastReturned + 1, arr, lastReturned, count - lastReturned - 1);
            count--;
            cursorBefore--;
            lastReturned = -1;
            returnedSomething = false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = cursorBefore;
            returnedSomething = true;
            return (T) arr[cursorBefore++];
        }
    }

    private class StoreListIterator extends StoreIterator implements ListIterator<T> {
        @Override
        public boolean hasPrevious() {
            return cursorBefore > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            cursorBefore--;
            lastReturned = cursorBefore;
            return (T) arr[cursorBefore];
        }

        @Override
        public int nextIndex() {
            return cursorBefore;
        }

        @Override
        public int previousIndex() {
            return cursorBefore - 1;
        }

        @Override
        public void set(T t) {
            if (!returnedSomething) {
                throw new IllegalStateException();
            }
            arr[lastReturned] = t;
        }

        @Override
        public void add(T t) {
            if (arr.length == count) {
                arr = Arrays.copyOf(arr, count + count / 2 + 1);
            }
            System.arraycopy(arr, cursorBefore, arr, cursorBefore + 1, count - cursorBefore);
            arr[cursorBefore++] = t;
            count++;
            lastReturned = -1;
            returnedSomething = false;
        }
    }
}
