package com.jimii.createpopplaythings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

final class MetadataTest {
    private static final Path METADATA = Path.of("src/main/resources/META-INF/neoforge.mods.toml");

    @Test
    void declaresExactRequiredRuntimeDependencies() throws IOException {
        String toml = Files.readString(METADATA);

        assertTrue(toml.contains("modId=\"create\""));
        assertTrue(toml.contains("versionRange=\"[6.0.10,6.0.11)\""));
        assertTrue(toml.contains("modId=\"jcsmp_pops\""));
        assertTrue(toml.contains("versionRange=\"[1.0.2,1.0.3)\""));
        assertTrue(toml.contains("modId=\"neoforge\""));
        assertTrue(toml.contains("versionRange=\"[21.1.233,21.2)\""));
    }

    @Test
    void buildDoesNotEmbedDependencyJars() throws IOException {
        String build = Files.readString(Path.of("build.gradle"));
        assertFalse(build.contains("shadowJar"));
        assertFalse(build.contains("jarJar("));
    }
}
