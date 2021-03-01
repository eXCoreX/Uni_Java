package com.excore.java_lab_2.test;

import com.excore.java_lab_2.model.Cylinder;
import com.excore.java_lab_2.model.IWeight;
import com.excore.java_lab_2.model.Timber;
import com.excore.java_lab_2.store.ProductStore;
import com.excore.java_lab_2.store.WoodDirectory;

public class TestApp {
    private final WoodDirectory wd = new WoodDirectory();
    private final ProductStore ps = new ProductStore();

    private float calcWeight() {
        float result = 0f;
        for (IWeight t :
                ps.getArr()) {
            result += t.weight();
        }
        return result;
    }

    private void startApp() {
        ps.add(new Timber(wd.get(1), 5f, 0.5f, 0.4f));
        ps.add(new Timber(wd.get(2), 10f, 0.5f, 0.4f));
        ps.add(new Cylinder(wd.get(3), 15f, 1f));
        ps.add(new Timber(wd.get(3), 11f, 0.9f, 1.4f));
        ps.add(new Cylinder(wd.get(1), 4f, 0.3f));

        System.out.println(wd);
        System.out.println(ps);

        System.out.printf("Загальна вага: %1.3f", calcWeight());
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
