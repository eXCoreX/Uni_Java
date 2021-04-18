package com.excore.java_lab_4.test;

import com.excore.java_lab_4.model.*;
import com.excore.java_lab_4.store.ProductStore;
import com.excore.java_lab_4.store.WoodDirectory;

import java.util.Iterator;
import java.util.ListIterator;

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

        System.out.printf("Загальна вага: %1.3f\n", calcWeight());

        System.out.println("Перелік виробів до вилучення:");
        System.out.println(ps);
        Iterator<IWeight> itr = ps.iterator();
        while (itr.hasNext()) {
            IWeight obj = itr.next();
            if (obj.weight() > 1.2) {
                itr.remove();
            }
        }
        System.out.println("Перелік виробів після вилучення:");
        System.out.println(ps);
        System.out.println("З доданим об'єктом: ");
        ListIterator<IWeight> listItr = ps.listIterator();
        try {
            listItr.add(new Cylinder(wd.get(1), 4f, 0.3f));
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
        System.out.println(ps);
        System.out.println("Спроба помилкового вилучення:");
        try {
            listItr.remove();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.startApp();
    }
}
