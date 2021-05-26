package com.excore.java_lab_8.model;

public class WoodProduct implements INamedProduct {
    private final int id;
    private final String name;
    private final String wood;
    private final float weight;

    public WoodProduct(int id, String name, String wood, float weight) {
        this.id = id;
        this.name = name;
        this.wood = wood;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getWood() {
        return wood;
    }

    @Override
    public float getWeight() {
        return weight;
    }
}
