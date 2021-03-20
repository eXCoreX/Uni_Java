package com.excore.java_lab_3_1.model;

public abstract class AbstractForm implements IWeight {
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
