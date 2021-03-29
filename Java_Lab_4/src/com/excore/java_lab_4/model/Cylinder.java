package com.excore.java_lab_4.model;

public class Cylinder extends AbstractForm {
    private final float length;
    private final float diameter;

    public Cylinder(Wood wood, float length, float diameter) throws Exception {
        super(wood);
        this.length = length;
        this.diameter = diameter;

        if (volume() > 25.0f) {
            throw new Exception("Cylinder volume can't be bigger than 25.0, instead got " + volume());
        }

        if (length > 10.0f || diameter > 10.0f) {
            throw new Exception("Length and diameter can't be bigger than 10.0, instead got " + length + " "
                    + diameter);
        }
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
