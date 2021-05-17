package com.excore.java_lab_7.test;

import java.util.*;

public class TestCollections {
    public static void test1() {
        Random rnd = new Random();
        Collection<Integer> c1 = new Vector<Integer>();
        for (int i = 0; i < 15; i++) {
            c1.add(rnd.nextInt(10));
        }
        System.out.println("Collection vector");
        System.out.println(c1);
        Collection<Integer> c2 = new TreeSet<Integer>();
        c2.addAll(c1);
        System.out.println("\nCollection TreeSet");
        c2.forEach((t) -> System.out.printf("%d ", t));
        System.out.println();
    }

    public static void test2() {
        Random rnd = new Random();
        Collection<Integer> c1 = new ArrayList<>();
        Collection<Integer> c2 = new LinkedHashSet<>();
        Collection<Integer> c3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            c1.add(rnd.nextInt(10));
            c2.add(rnd.nextInt(10));
        }

        c3.addAll(c1); c3.removeAll(c2);
        System.out.println(c1 + " removeAll " + c2 + " = " + c3);

        c3.clear(); c3.addAll(c2); c3.removeAll(c1);
        System.out.println(c2 + " removeAll " + c1 + " = " + c3);

        c3.clear(); c3.addAll(c1); c3.retainAll(c2);
        System.out.println(c1 + " retainAll " + c2 + " = " + c3);

        c3.clear(); c3.addAll(c2); c3.retainAll(c1);
        System.out.println(c2 + " retainAll " + c1 + " = " + c3);
    }

    public static void test3() {
        Random rnd = new Random();
        Collection<Integer> c1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c1.add(rnd.nextInt(10));
        }
        Collection<Integer> c2 = new LinkedHashSet<>();
        c2.addAll(c1);
        boolean b1 = c1.containsAll(c2);
        System.out.println(c1 + " containsAll " + c2 + " = " + b1);

        Collection<Integer> c3 = new TreeSet<>();
        c3.addAll(c1);
        boolean b2 = c1.containsAll(c3);
        System.out.println(c1 + " containsAll " + c3 + " = " + b2);
    }

    public static void test4() {
        Random rnd = new Random();
        List<Integer> c1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            c1.add(rnd.nextInt(10));
        }
        System.out.println("Before sort:\n" + c1);
        c1.sort((x, y) -> x - y);
        System.out.println("After sort:\n" + c1);
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
