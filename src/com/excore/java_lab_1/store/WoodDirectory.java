package com.excore.java_lab_1.store;

import com.excore.java_lab_1.model.Wood;

import java.util.Arrays;

public class WoodDirectory {
    private int count = 0;
    private Wood[] arr = new Wood[3];

    {
        arr[0] = new Wood(1, "Модрина", 1.1f);
        arr[1] = new Wood(2, "Ялина", 0.9f);
        arr[2] = new Wood(3, "Сосна", 0.7f);
        count = 3;
    }

    public Wood[] getArr() {
        return Arrays.copyOf(arr, count);
    }

    public Wood get(int id) {
        for (int i = 0; i < count; i++) {
            if (arr[i].getId() == id) {
                return arr[i];
            }
        }
        return null; // Not found
    }

    public boolean add(Wood newWood) {
        if (get(newWood.getId()) != null) {
            return false; // Id already present
        }

        if (arr.length == count) {
            arr = Arrays.copyOf(arr, count + count / 2);
        }
        arr[count++] = newWood;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Каталог деревини:\n");

        for (int i = 0; i < count; i++) {
            sb.append(arr[i]).append('\n');
        }
        return sb.toString();
    }
}
