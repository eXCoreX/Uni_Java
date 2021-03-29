package com.excore.java_lab_4.model;

public class Timber extends AbstractForm {
    private final float length;
    private final float height;
    private final float width;

    public Timber(Wood wood, float length, float height, float width) throws Exception {
        super(wood);
        this.length = length;
        this.height = height;
        this.width = width;
        if (volume() > 25.0f) {
            throw new Exception("Timber volume can't be bigger than 25.0, instead got " + volume());
        }

        if (length > 10.0f || height > 10.0f || width > 10.0f) {
            throw new Exception("Length, height and width can't be bigger than 10.0, instead got " + length + " "
                    + height + " " + width);
        }
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
