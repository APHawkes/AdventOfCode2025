package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Day04A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    int maxRow = 0;
    int maxCol = 0;

    //read data into grid
    char[][] grid = new char[200][200];//declare too big, don't care about waste
    int currentRow = 0;
    while ((line = br.readLine()) != null) {
      for(int currentCol=0; currentCol<line.length(); currentCol++) {
        grid[currentRow][currentCol] = line.charAt(currentCol);

      }
      currentRow++;
      maxCol = line.length();
    }
    maxRow = currentRow;

    //evaluate each position
    int accessibleRolls = 0;
    for (int row = 0; row < maxRow; row++) {
      for (int col = 0; col < maxCol; col++) {
        if (grid[row][col] == '@') { //paper roll
          int count =
              isPaperAt(grid, row-1, col-1) +
              isPaperAt(grid, row-1, col+0) +
              isPaperAt(grid, row-1, col+1) +
              isPaperAt(grid, row+0, col-1) +
              isPaperAt(grid, row+0, col+1) +
              isPaperAt(grid, row+1, col-1) +
              isPaperAt(grid, row+1, col+0) +
              isPaperAt(grid, row+1, col+1);
          if (count < 4) {
            grid[row][col] = 'x';
            accessibleRolls++;
          }

        }
      }
    }
    System.out.println(accessibleRolls);
    System.out.println(Arrays.deepToString(grid));
  }

  private static int isPaperAt(char[][] grid, int row, int col) {
    if (row < 0 || col < 0) return 0;
    return grid[row][col] == '@' || grid[row][col] == 'x'? 1 : 0;
  }
}
