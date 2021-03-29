package com.excore.java_lab_4.model;

import java.io.Serializable;

public abstract class AbstractForm implements IWeight, Serializable {
    protected final Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public Wood getWood() {
        return wood;
    }

    public abstract float volume();

    @Override
    public float weight() {
        return volume() * wood.getDensity();
    }
}
