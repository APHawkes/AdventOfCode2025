package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Day07B {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    int colCount = 0;

    //read data into map grid
    char[][] mapGrid = new char[200][200];//declare too big, don't care about waste
    long[][] countGrid = new long[200][200];//declare too big, don't care about waste
    int currentRow = 0;
    while ((line = br.readLine()) != null) {
      for(int currentCol=0; currentCol<line.length(); currentCol++) {
        mapGrid[currentRow][currentCol] = line.charAt(currentCol);
      }
      currentRow++;
      colCount = Math.max(line.length(), colCount);
    }
    int rowCount = currentRow;

    //evaluate each position
    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
        //Look above to see if a beam is coming down
        var currentChar = mapGrid[row][col];
        if (currentChar == 'S' && row==0) { //we only do row zero for the 'S' (source)
          countGrid[row][col] = 1;
        }
        if (row == 0){
          continue;
        }
        var aboveVal = countGrid[row-1][col];
        if (currentChar == '^') {
          aboveVal = countGrid[row-1][col];
          if (col-1 >=0)
            countGrid[row][col-1] += aboveVal;
          if (col+1 <=colCount)
            countGrid[row][col+1] += aboveVal;
        } else {
          countGrid[row][col] += aboveVal;
        }
      }
    }
    //iterated all the way down.
    //add all the values from the bottom row
    var sum = Arrays.stream(countGrid[rowCount-1]).sum();
    System.out.println(sum);
  }

}
