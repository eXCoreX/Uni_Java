package com.excore.java_lab_5.test;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class TestFile {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        JFileChooser dialog = new JFileChooser();

        dialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
        dialog.setDialogTitle("Виберіть потрібні файли і папки");
        dialog.setApproveButtonText("Відкрити");
        dialog.setMultiSelectionEnabled(true);
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
                return "Файли типу *.txt";
            }
        });
        try {
            EventQueue.invokeAndWait(() -> {
                dialog.showOpenDialog(null);
                File[] ff = dialog.getSelectedFiles();
                if (ff != null) {
                    for (File file:
                            ff) {
                        System.out.println(file.getName());
                        System.out.println(file.getAbsolutePath());
                    }
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
