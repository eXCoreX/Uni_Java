package com.excore.java_lab_3_2.test;

import com.excore.java_lab_3_2.model.Cylinder;
import com.excore.java_lab_3_2.model.IWeight;
import com.excore.java_lab_3_2.model.Timber;
import com.excore.java_lab_3_2.model.Waste;
import com.excore.java_lab_3_2.store.ProductStore;
import com.excore.java_lab_3_2.store.WoodDirectory;

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
        try {
            ps.add(new Timber(wd.get(1), 5f, 0.5f, 0.4f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        try {
            ps.add(new Timber(wd.get(2), 10f, 0.5f, 0.4f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        try {
            ps.add(new Waste(3.6f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        try {
            ps.add(new Cylinder(wd.get(3), 15f, 1f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        try {
            ps.add(new Timber(wd.get(3), 11f, 0.9f, 1.4f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        try {
            ps.add(new Cylinder(wd.get(1), 4f, 0.3f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        try {
            ps.add(new Waste(9.2f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }

        System.out.println(wd);
        System.out.println(ps);

        System.out.printf("Загальна вага: %1.3f", calcWeight());
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
