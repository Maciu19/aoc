package org.example.aoc2024.day1;

import org.example.utils.Day;
import org.example.utils.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day1 implements Day {

    @Override
    public void Part1() {
        var lists = processInputFile();

        Collections.sort(lists.a);
        Collections.sort(lists.b);

        int sum = 0;
        for(int i = 0; i < lists.a.size(); i++) {
            sum += Math.abs(lists.a.get(i) - lists.b.get(i));
        }

        System.out.println(sum);
    }

    @Override
    public void Part2() {
        var lists = processInputFile();

        int sum = 0;
        for(int i = 0; i < lists.a.size(); i++) {
            sum += lists.a.get(i) * Collections.frequency(lists.b, lists.a.get(i));
        }

        System.out.println(sum);
    }

    private Pair<List<Integer>, List<Integer>> processInputFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Day.INPUT_FILE_PATH + "/day1/day1.txt"));

            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();

            for(String line: lines) {
                String[] arr = line.split(" {3}");

                leftList.add(Integer.parseInt(arr[0]));
                rightList.add(Integer.parseInt(arr[1]));
            }

            return new Pair<>(leftList, rightList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
