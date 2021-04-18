package com.excore.java_lab_6.model;

import java.io.Serializable;

public class Waste implements IWeight, Serializable {
    private final float weight;

    public Waste(float wasteWeight) throws Exception {
        if (wasteWeight < 0.02f || wasteWeight > 0.100f) {
            throw new Exception("Waste weight must be from 0.020 to 0.100, instead got " + wasteWeight);
        }
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
