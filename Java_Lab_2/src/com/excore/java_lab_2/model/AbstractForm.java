package com.excore.java_lab_2.model;

public abstract class AbstractForm {
    protected final Wood wood;

    public AbstractForm(Wood wood) {
        this.wood = wood;
    }

    public Wood getWood() {
        return wood;
    }

    public abstract float volume();

    public float weight() {
        return volume() * wood.getDensity();
    }
}
