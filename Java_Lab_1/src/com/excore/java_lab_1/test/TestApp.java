package com.excore.java_lab_1.test;

import com.excore.java_lab_1.model.Timber;
import com.excore.java_lab_1.model.Wood;
import com.excore.java_lab_1.store.ProductStore;
import com.excore.java_lab_1.store.WoodDirectory;

public class TestApp {
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();

    private float calcWeight() {
        float result = 0f;
        for (Timber t :
                ps.getArr()) {
            result += t.weight();
        }
        return result;
    }

    private void startApp() {
        ps.add(new Timber(wd.get(1), 5f, 0.5f, 0.4f));
        ps.add(new Timber(wd.get(2), 10f, 0.5f, 0.4f));

        System.out.println(wd);
        System.out.println(ps);

        System.out.printf("Загальна вага: %1.3f", calcWeight());
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
