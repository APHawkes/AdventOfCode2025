package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Day07A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    int colCount = 0;

    //read data into grid
    char[][] grid = new char[200][200];//declare too big, don't care about waste
    int currentRow = 0;
    while ((line = br.readLine()) != null) {
      for(int currentCol=0; currentCol<line.length(); currentCol++) {
        grid[currentRow][currentCol] = line.charAt(currentCol);

      }
      currentRow++;
      colCount = Math.max(line.length(), colCount);
    }
    int rowCount = currentRow;

    //evaluate each position
    int splits = 0;
    for (int row = 1; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
        //Look above to see if a beam is coming down
        var aboveChar = grid[row-1][col];
        var currentChar = grid[row][col];
        if (aboveChar == '|' || aboveChar == 'S') {
          //there's a beam to propagate
          if (currentChar == '^') {  //split beam
            splits++;
            if (col-1 >= 0) {
              grid[row][col-1] = '|';
            }
            if (col+1 < colCount) {
              grid[row][col+1] = '|';
            }
          } else { //drop beam down
            grid[row][col] = '|';
          }
        }
      }
    }
    //iterated all the way down.
    System.out.println(Arrays.deepToString(grid));
    System.out.println(splits);
  }

}
