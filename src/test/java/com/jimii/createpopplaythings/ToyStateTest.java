package com.jimii.createpopplaythings;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

final class ToyStateTest {
    @Test
    void stateSerializationNamesRemainStable() throws IOException {
        String robot = Files.readString(Path.of("src/main/java/com/jimii/createpopplaythings/content/RobotAssembly.java"));
        String sand = Files.readString(Path.of("src/main/java/com/jimii/createpopplaythings/content/SandCreation.java"));

        assertTrue(robot.contains("CORRECT(\"correct\")"));
        assertTrue(robot.contains("INCORRECT(\"incorrect\")"));
        assertTrue(robot.contains("PARTS(\"parts\")"));
        assertTrue(sand.contains("EMPTY(\"empty\")"));
        assertTrue(sand.contains("SANDCASTLE(\"sandcastle\")"));
        assertTrue(sand.contains("PILES(\"piles\")"));
        assertTrue(sand.contains("TUNNEL(\"tunnel\")"));
        assertTrue(sand.contains("ROAD(\"road\")"));
    }
}
