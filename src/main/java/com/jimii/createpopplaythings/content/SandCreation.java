package com.jimii.createpopplaythings.content;

import net.minecraft.util.StringRepresentable;

public enum SandCreation implements StringRepresentable {
    EMPTY("empty"), SANDCASTLE("sandcastle"), PILES("piles"), TUNNEL("tunnel"), ROAD("road");
    private final String name;
    SandCreation(String name) { this.name = name; }
    @Override public String getSerializedName() { return name; }
}
