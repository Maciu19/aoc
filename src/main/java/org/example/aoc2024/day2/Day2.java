package org.example.aoc2024.day2;

import org.example.utils.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day2 implements Day {

    @Override
    public void Part1() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Day.INPUT_FILE_PATH + "/day2/day2.txt"));
            List<List<Integer>> reports = new ArrayList<>();

            for(String line : lines) {
                String[] numbers = line.split(" ");
                List<Integer> levels = new ArrayList<>();

                for(String numberString: numbers) {
                    int number = Integer.parseInt(numberString);
                    levels.add(number);
                }

                reports.add(levels);
            }

            int safeCounter = 0;
            for(List<Integer> report : reports) {
                if(isSafe(report)) {
                    safeCounter++;
                }
            }

            System.out.println(safeCounter);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Part2() {

    }

    private boolean isSafe(List<Integer> arr) {
        boolean increasingOrder = true;

        for (int i = 0; i < arr.size() - 1; i++) {
            int subtract = arr.get(i) - arr.get(i + 1);
            int difference = Math.abs(subtract);

            if(!(difference >= 1 && difference <= 3))
                return false;

            if (i == 0 && subtract < 0) {
                increasingOrder = false;
            }

            if (subtract > 0 && !increasingOrder)
                return false;

            if (subtract < 0 && increasingOrder)
                return false;
        }

        return true;
    }
}
