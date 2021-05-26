package com.excore.java_lab_8.model;

import java.io.Serializable;

public class Waste implements INamedProduct, Serializable {
    private final float weight;

    public Waste(float wasteWeight) throws Exception {
        if (wasteWeight < 0.02f || wasteWeight > 0.100f) {
            throw new Exception("Waste weight must be from 0.020 to 0.100, instead got " + wasteWeight);
        }
        this.weight = wasteWeight;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "weight=" + weight +
                '}';
    }

    @Override
    public String getName() {
        return "Waste";
    }
}
