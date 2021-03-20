package com.excore.java_lab_3_2.test;

import com.excore.java_lab_3_2.model.*;
import com.excore.java_lab_3_2.store.ProductStore;
import com.excore.java_lab_3_2.store.WoodDirectory;

import java.util.Scanner;

public class TestByConsole {
    private final WoodDirectory wd = new WoodDirectory();
    private final ProductStore ps = new ProductStore();
    private final Scanner s = new Scanner(System.in);
    private void addWood() {
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
        if (wd.addChecked(new Wood(id, name, density))) {
            System.out.println("\nWood added");
            System.out.println(wd);
        } else {
            System.out.println("\nWood with id " + id + " already exists");
        }
    }

    private void addTimber() {
        int id;
        float l, h, w;

        System.out.print("Wood Id: ");
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

        System.out.println();
        Wood wood = wd.get(id);
        if (wood != null) {
            try {
                ps.add(new Timber(wood, l, h, w));
                System.out.println("Timber added");
            } catch (Exception e) {
                MyExceptionHandler.handle(e, "Введення продуктів");
            }
        } else {
            System.out.println("Wood not found");
        }
    }

    private void addCylinder() {
        int id;
        float l, d;

        System.out.print("Wood Id: ");
        id = s.nextInt();
        s.nextLine();
        System.out.print("Length: ");
        l = s.nextFloat();
        s.nextLine();
        System.out.print("Diameter: ");
        d = s.nextFloat();
        s.nextLine();

        System.out.println();
        Wood wood = wd.get(id);
        if (wood != null) {
            try {
                ps.add(new Cylinder(wood, l, d));
                System.out.println("Cylinder added");
            } catch (Exception e) {
                MyExceptionHandler.handle(e, "Введення продуктів");
            }
        } else {
            System.out.println("Wood not found");
        }
    }

    private void addWaste() {
        System.out.print("Weight: ");
        float w = s.nextFloat();
        s.nextLine();
        try {
            ps.add(new Waste(w));
            System.out.println("\nWaste added");
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Введення продуктів");
        }
    }

    private void addProduct() {
        System.out.println(
                """
                        What do you want to add?
                        1: Timber
                        2: Cylinder
                        3: Waste""");
        int prod = s.nextInt();
        s.nextLine();
        System.out.println();
        switch (prod) {
            case 1 -> addTimber();
            case 2 -> addCylinder();
            case 3 -> addWaste();
            default -> {
                return;
            }
        }

        System.out.println(ps);

    }

    private void calcWeight() {
        float result = 0f;
        for (IWeight t :
                ps.getArr()) {
            result += t.weight();
        }
        System.out.printf("Total weight: %1.3f\n", result);
    }

    private void startApp() {
        System.out.println("/ / / Timber management system \\ \\ \\");

        while (true) {
            System.out.println(
                    """
                            
                            1: Add Wood
                            2: Add Product
                            3: Calculate total weight
                            4: Exit""");
            int choice = s.nextInt();
            s.nextLine();
            System.out.println();
            switch (choice) {
                case 1 -> addWood();
                case 2 -> addProduct();
                case 3 -> calcWeight();
                case 4 -> System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        TestByConsole app = new TestByConsole();
        app.startApp();
    }
}
