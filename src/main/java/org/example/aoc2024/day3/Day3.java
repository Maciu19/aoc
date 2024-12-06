package org.example.aoc2024.day3;

import org.example.utils.Day;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements Day {

    @Override
    public void Part1() {
        try {
            String text = Files.readString(Paths.get(Day.INPUT_FILE_PATH + "/day3/day3.txt"));
            char[] textAsCharArray = text.toCharArray();

            String multiplicationRegex = "^mul\\((\\d{1,3}),(\\d{1,3})\\)$";
            Pattern multiplicationPattern = Pattern.compile(multiplicationRegex);
            Matcher multiplicationMatcher;

            int result = 0;
            for(int i = 0; i < textAsCharArray.length; i++){
                for (int j = 8; j < 13; j++) {
                    if (i + j < textAsCharArray.length) {
                        String extractedText = text.substring(i, i +j);
                        multiplicationMatcher = multiplicationPattern.matcher(extractedText);

                        if (multiplicationMatcher.matches()) {
                            int number1 = Integer.parseInt(multiplicationMatcher.group(1));
                            int number2 = Integer.parseInt(multiplicationMatcher.group(2));

                            result += number1 * number2;
                        }
                    }
                }
            }

            System.out.println(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Part2() {
        try {
            String text = Files.readString(Paths.get(Day.INPUT_FILE_PATH + "/day3/day3.txt"));
            char[] textAsCharArray = text.toCharArray();

            String multiplicationRegex = "^mul\\((\\d{1,3}),(\\d{1,3})\\)$";
            Pattern multiplicationPattern = Pattern.compile(multiplicationRegex);
            Matcher multiplicationMatcher;

            boolean canDo = true;

            int result = 0;
            for(int i = 0; i < textAsCharArray.length; i++){

                if (text.startsWith("do()", i)) {
                    canDo = true;
                    continue;
                } else if (text.startsWith("don't()", i)) {
                    canDo = false;
                    continue;
                }


                for (int j = 8; j < 13; j++) {
                    if (i + j < textAsCharArray.length) {
                        String extractedText = text.substring(i, i +j);
                        multiplicationMatcher = multiplicationPattern.matcher(extractedText);

                        if (multiplicationMatcher.matches() && canDo) {
                            int number1 = Integer.parseInt(multiplicationMatcher.group(1));
                            int number2 = Integer.parseInt(multiplicationMatcher.group(2));

                            result += number1 * number2;
                        }
                    }
                }
            }

            System.out.println(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
