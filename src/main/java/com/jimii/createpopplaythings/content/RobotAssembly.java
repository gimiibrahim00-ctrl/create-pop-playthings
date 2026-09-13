package com.jimii.createpopplaythings.content;

import net.minecraft.util.StringRepresentable;

public enum RobotAssembly implements StringRepresentable {
    CORRECT("correct"), INCORRECT("incorrect"), PARTS("parts");
    private final String name;
    RobotAssembly(String name) { this.name = name; }
    @Override public String getSerializedName() { return name; }
}
