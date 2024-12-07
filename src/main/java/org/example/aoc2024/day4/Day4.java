package org.example.aoc2024.day4;

import org.example.utils.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day4 implements Day {

    private String searchWord = "XMAS";
    private String reverseSearchWord = "SAMX";

    @Override
    public void Part1() {
        char[][] inputMatrix = getInputAsMatrix();

        int searchWordFindings = 0;
        for(int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                searchWordFindings += findNumberOfApperancesFromPosition(inputMatrix, i, j);
            }
        }

        System.out.println(searchWordFindings);

    }

    @Override
    public void Part2() {
        char[][] inputMatrix = getInputAsMatrix();

        int xmasFindings = 0;
        for(int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix.length; j++) {
                if (hasNeighborsThatFormAnXMAS(inputMatrix, i, j))
                    xmasFindings++;

            }
        }

        System.out.println(xmasFindings);
    }

    private char[][] getInputAsMatrix() {
        String text = null;
        try {
            text = Files.readString(Paths.get(Day.INPUT_FILE_PATH + "/day4/day4.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] textSplit = text.split("\n");
        char[][] matrix = new char[140][140];

        for (int i = 0; i < textSplit.length; i++) {
            char[] textSplitAsCharArray = textSplit[i].toCharArray();
            for(int j = 0; j < textSplitAsCharArray.length; j++) {
                matrix[i][j] = textSplitAsCharArray[j];
            }
        }

        return matrix;
    }

    private boolean hasNeighborsThatFormAnXMAS(char[][] matrix, int x, int y) {
        if (matrix[x][y] != 'A') {
            return false;
        }

        if (x - 1 > -1 && y - 1 > -1 &&
            x + 1 < matrix.length && y + 1 < matrix.length) {

            char upLeft = matrix[x - 1][y - 1];
            char upRight = matrix[x - 1][y + 1];
            char downLeft = matrix[x + 1][y - 1];
            char downRight = matrix[x + 1][y + 1];

            if ((upLeft == 'M' && downRight == 'S' || upLeft == 'S' && downRight == 'M') &&
                (downLeft == 'M' && upRight == 'S' || downLeft == 'S' && upRight == 'M')) {
                return true;
            }

        }

        return false;
    }

    private int findNumberOfApperancesFromPosition(char[][] matrix, int x, int y) {
        int numberOfAppeareances = 0;
        int buffer = searchWord.length() - 1;

        // Horizontal
        if (y + buffer < matrix.length) {
            String word = extractSubArrayAndConvertToString(matrix, x, y, x, y + buffer);

            if (word.equals(searchWord) || word.equals(reverseSearchWord)) {
                numberOfAppeareances++;
            }
        }

        // Vertical
        if (x + buffer < matrix.length) {
            String word = extractSubArrayAndConvertToString(matrix, x, y, x + buffer, y);

            if (word.equals(searchWord) || word.equals(reverseSearchWord)) {
                numberOfAppeareances++;
            }
        }

        // Diagonal \
        if (x + buffer < matrix.length && y + buffer < matrix.length) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < searchWord.length(); i++) {
                sb.append(matrix[x + i][y + i]);
            }

            if (sb.toString().equals(searchWord) || sb.toString().equals(reverseSearchWord)) {
                numberOfAppeareances++;
            }
        }

        // Diagonal /
        if (x - buffer > -1 && y + buffer < matrix.length) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < searchWord.length(); i++) {
                sb.append(matrix[x - i][y + i]);
            }

            if (sb.toString().equals(searchWord) || sb.toString().equals(reverseSearchWord)) {
                numberOfAppeareances++;
            }
        }

        return numberOfAppeareances;

    }

    private String extractSubArrayAndConvertToString(char[][] matrix, int a1, int b1, int a2, int b2) {
        StringBuilder sb = new StringBuilder();

        for (int i = a1; i <= a2; i++) {
            for (int j = b1; j <= b2; j++) {
                sb.append(matrix[i][j]);
            }
        }

        return sb.toString();
    }
}
