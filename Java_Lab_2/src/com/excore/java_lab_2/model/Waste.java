package com.excore.java_lab_2.model;

public class Waste implements IWeight{
    private final float weight;

    public Waste(float wasteWeight) {
        this.weight = wasteWeight;
    }

    public float weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "weight=" + weight +
                '}';
    }
}
