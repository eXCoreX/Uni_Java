package com.excore.java_lab_2.model;

public class Cylinder extends AbstractForm {
    private final float length;
    private final float diameter;

    public Cylinder(Wood wood, float length, float diameter) {
        super(wood);
        this.length = length;
        this.diameter = diameter;
    }

    public float getLength() {
        return length;
    }

    public float getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "wood=" + wood.getName() +
                ", weight=" + weight() +
                '}';
    }

    @Override
    public float volume() {
        return (float) (Math.PI * diameter * diameter / 4 * length);
    }
}
