package com.excore.java_lab_1.test;

import com.excore.java_lab_1.model.Timber;
import com.excore.java_lab_1.model.Wood;
import com.excore.java_lab_1.store.ProductStore;
import com.excore.java_lab_1.store.WoodDirectory;

import java.util.Scanner;

public class TestByConsole {
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();

    private void addWood() {
        Scanner s = new Scanner(System.in);
        int id;
        String name;
        float density;

        System.out.print("Id: ");
        id = s.nextInt();
        s.nextLine();
        System.out.print("Name: ");
        name = s.nextLine();
        System.out.print("Density: ");
        density = s.nextFloat();
        s.nextLine();
        if (wd.add(new Wood(id, name, density))) {
            System.out.println("Wood added");
        } else {
            System.out.println("Wood with id " + id + " already exists");
        }
    }

    private void addTimber() {
        Scanner s = new Scanner(System.in);
        int id;
        float l, h, w;

        System.out.print("Id: ");
        id = s.nextInt();
        s.nextLine();
        System.out.print("Length: ");
        l = s.nextFloat();
        s.nextLine();
        System.out.print("Height: ");
        h = s.nextFloat();
        s.nextLine();
        System.out.print("Width: ");
        w = s.nextFloat();
        s.nextLine();
        Wood wood = wd.get(id);
        if (wood != null) {
            ps.add(new Timber(wood, l, h, w));
            System.out.println("Timber added");
        } else {
            System.out.println("Wood not found");
        }
    }

    private void calcWeight() {
        float result = 0f;
        for (Timber t :
                ps.getArr()) {
            result += t.weight();
        }
        System.out.printf("Total weight: %1.3f\n", result);
    }

    private void startApp() {
        System.out.println("/ / / Timber management system \\ \\ \\");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1: Add wood");
            System.out.println("2: Add timber");
            System.out.println("3: Calculate total weight");
            System.out.println("4: Exit");
            Scanner s = new Scanner(System.in);
            int choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1:
                    addWood();
                    break;
                case 2:
                    addTimber();
                    break;
                case 3:
                    calcWeight();
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void main(String[] args) {
        TestByConsole app = new TestByConsole();
        app.startApp();
    }
}
