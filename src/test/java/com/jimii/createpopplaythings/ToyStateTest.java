package com.jimii.createpopplaythings;

import com.jimii.createpopplaythings.content.RobotAssembly;
import com.jimii.createpopplaythings.content.SandCreation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class ToyStateTest {
    @Test void stableRobotNames() {
        assertEquals("correct", RobotAssembly.CORRECT.getSerializedName());
        assertEquals("incorrect", RobotAssembly.INCORRECT.getSerializedName());
        assertEquals("parts", RobotAssembly.PARTS.getSerializedName());
    }
    @Test void fiveSandCreations() { assertEquals(5, SandCreation.values().length); }
}
