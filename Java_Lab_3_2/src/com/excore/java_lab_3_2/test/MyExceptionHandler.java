package com.excore.java_lab_3_2.test;

import javax.swing.*;

public class MyExceptionHandler {
    public static void handle(Exception e, String title) {
        JOptionPane.showMessageDialog(null, e.getMessage(), title, JOptionPane.ERROR_MESSAGE);
    }
}
