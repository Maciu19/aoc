package org.example.utils;

import java.nio.file.Paths;

public interface Day {
    String INPUT_FILE_PATH = Paths.get("").toAbsolutePath() +
                            "/src/main/java/org/example/aoc2024/";

    void Part1();
    void Part2();
}
