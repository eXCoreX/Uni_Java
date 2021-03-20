package com.excore.java_lab_3_2.test;

import com.excore.java_lab_3_2.model.*;
import com.excore.java_lab_3_2.store.ProductStore;
import com.excore.java_lab_3_2.store.WoodDirectory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestByConsole {
    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();
    private final Scanner s = new Scanner(System.in);
    private final BufferedWriter bw = new BufferedWriter(new FileWriter("Log.txt"));

    public TestByConsole() throws IOException {
        LogString("Program start");
    }

    public void close() {
        LogString("Program shutting down...");
        try {
            bw.close();
        } catch (IOException e) {
            MyExceptionHandler.handle(e, "Shutting down");
        }
    }

    private String getCurrentDateTime() {
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return formatter.format(current);
    }

    private void LogString(String s) {
        try {
            bw.write(getCurrentDateTime() + ": " + s + "\n");
        } catch (IOException e) {
            MyExceptionHandler.handle(e, "Logging");
        }
    }

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
        Wood toAdd = new Wood(id, name, density);
        if (wd.addChecked(toAdd)) {
            System.out.println("\nWood added");
            System.out.println(wd);
            LogString("Added " + toAdd);
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
                Timber toAdd = new Timber(wood, l, h, w);
                ps.add(toAdd);
                System.out.println("Timber added");
                LogString("Added " + toAdd);
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
                Cylinder toAdd = new Cylinder(wood, l, d);
                ps.add(toAdd);
                System.out.println("Cylinder added");
                LogString("Added " + toAdd);
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
            Waste toAdd = new Waste(w);
            ps.add(toAdd);
            System.out.println("\nWaste added");
            LogString("Added " + toAdd);
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

    private void printToFile() {
        JFileChooser dialog = preparePrintDialog();

        try {
            EventQueue.invokeAndWait(() -> {
                dialog.showSaveDialog(null);

                File f = dialog.getSelectedFile();

                if (f != null) {
                    if (!f.getName().endsWith(".txt")) {
                        f = new File(f.getName() + ".txt");
                    }
                    try {
                        FileWriter fw = new FileWriter(f);
                        BufferedWriter bw = new BufferedWriter(fw);

                        bw.write(wd.toString() + "\n");
                        bw.write(ps.toString());
                        bw.close();
                        System.out.println("Printed successfully");
                    } catch (Exception e) {
                        MyExceptionHandler.handle(e, "Printing to file");
                    }
                } else {
                    System.out.println("Action cancelled");
                }
            });
        } catch (Exception e) {
            MyExceptionHandler.handle(e, "Printing to file");
        }
    }

    private void loadFromFile() {
        JFileChooser dialog = prepareSerializationDialog();

        try {
            EventQueue.invokeAndWait(() -> {
                dialog.showOpenDialog(null);

                File f = dialog.getSelectedFile();

                if (f != null) {
                    try {
                        FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream ois = new ObjectInputStream(fis);

                        wd = (WoodDirectory) ois.readObject();
                        ps = (ProductStore) ois.readObject();
                        System.out.println("File opened successfully");
                        System.out.println("Data:");
                        System.out.println(wd);
                        System.out.println(ps);
                        LogString("File loaded: " + f.getName());
                    } catch (Exception e) {
                        MyExceptionHandler.handle(e, "Opening file");
                    }
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            MyExceptionHandler.handle(e, "Opening file");
        }
    }

    private void saveToFile() {
        JFileChooser dialog = prepareSerializationDialog();
        try {
            EventQueue.invokeAndWait(() -> {
                dialog.showSaveDialog(null);

                File f = dialog.getSelectedFile();

                if (f != null) {
                    if (!f.getName().endsWith(".prodstore")) {
                        f = new File(f.getName() + ".prodstore");
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(f);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);

                        oos.writeObject(wd);
                        oos.writeObject(ps);
                        oos.close();
                        System.out.println("Saved successfully");
                    } catch (Exception e) {
                        MyExceptionHandler.handle(e, "Saving file");
                    }
                } else {
                    System.out.println("Saving action cancelled");
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            MyExceptionHandler.handle(e, "Saving file");
        }
    }

    private static JFileChooser prepareSerializationDialog() {
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f != null) {
                    return f.isDirectory() || (f.isFile() && f.getName().toLowerCase().endsWith(".prodstore"));
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Product store file (.prodstore)";
            }
        });
        return dialog;
    }

    private static JFileChooser preparePrintDialog() {
        JFileChooser dialog = new JFileChooser();
        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f != null) {
                    return f.isDirectory() || (f.isFile() && f.getName().toLowerCase().endsWith(".txt"));
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Text file (.txt)";
            }
        });
        return dialog;
    }

    private void startApp() {
        requestName();
        System.out.println("/ / / Timber management system \\ \\ \\");

        mainLoop:
        while (true) {
            System.out.println(
                    """
                                                        
                            1: Add Wood
                            2: Add Product
                            3: Calculate total weight
                            4: Save products to file
                            5: Load products from file
                            6: Print products to file
                            7: Exit""");
            int choice = s.nextInt();
            s.nextLine();
            System.out.println();
            switch (choice) {
                case 1 -> addWood();
                case 2 -> addProduct();
                case 3 -> calcWeight();
                case 4 -> saveToFile();
                case 5 -> loadFromFile();
                case 6 -> printToFile();
                case 7 -> {
                    break mainLoop;
                }
            }
        }
        close();
    }

    private void requestName() {
        System.out.print("Enter your name: ");
        String name = s.nextLine();
        System.out.println("\nHello, " + name);
        LogString(name + " logged in");
    }

    public static void main(String[] args) {
        try {
            TestByConsole app = new TestByConsole();
            app.startApp();
        } catch (IOException e) {
            MyExceptionHandler.handle(e, "Starting logging");
        }
    }
}
