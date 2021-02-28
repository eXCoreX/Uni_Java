package com.excore.java_lab_2.model;

public class Timber extends AbstractForm {
    private final float length;
    private final float height;
    private final float width;

    public Timber(Wood wood, float length, float height, float width) {
        super(wood);
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public float volume() {
        return length * height * width;
    }

    @Override
    public String toString() {
        return "Timber{" +
                "wood=" + wood.getName() +
                ", weight=" + weight() +
                '}';
    }
}
