package com.excore.java_lab_8.model;

import java.io.Serializable;

public abstract class AbstractForm implements INamedProduct, Serializable {
    protected final Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public Wood getWood() {
        return wood;
    }

    public abstract float volume();

    @Override
    public float getWeight() {
        return volume() * wood.getDensity();
    }
}
