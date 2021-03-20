package com.excore.java_lab_3_2.store;

import com.excore.java_lab_3_2.model.Wood;

import java.util.Arrays;

public class WoodDirectory extends AbstractStore<Wood> {

    {
        arr[0] = new Wood(1, "Модрина", 1.1f);
        arr[1] = new Wood(2, "Ялина", 0.9f);
        arr[2] = new Wood(3, "Сосна", 0.7f);
        count = 3;
    }

    public Wood[] getArr() {
        return Arrays.copyOf(arr, count, Wood[].class);
    }

    @Override
    public Wood get(int id) {
        for (int i = 0; i < count; i++) {
            if (((Wood)arr[i]).getId() == id) {
                return (Wood)arr[i];
            }
        }
        return null; // Not found
    }

    public boolean addChecked(Wood newWood) {
        if (get(newWood.getId()) != null) {
            return false; // Id already present
        }

        super.add(newWood);
        return true;
    }

    @Override
    public String toString() {
        return "Каталог деревини:\n" + super.toString();
    }
}
