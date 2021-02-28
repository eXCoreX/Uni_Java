package com.excore.java_lab_2.model;

public class Waste implements IWeight{
    private float wasteWeight;

    public Waste(float wasteWeight) {
        this.wasteWeight = wasteWeight;
    }

    public float weight() {
        return wasteWeight;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "weight=" + weight() +
                '}';
    }
}
