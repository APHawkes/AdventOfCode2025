package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Day06A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;

    String[][] grid = new String[10][5000];
    int row = 0;
    int col = 0;
    while ((line = br.readLine()) != null) {
      col = 0;
      StringTokenizer tokenizer = new StringTokenizer(line, " ");
      while (tokenizer.hasMoreTokens()) {
        grid[row][col] = tokenizer.nextToken();
        col++;
      }
      row++;
    }
    int colCount = col;
    int rowCount = row;

    BigInteger total = new BigInteger("0");
    for(col = 0; col < colCount; col++){
      Long accumulator = null;
      String operator = null;
      for(row = rowCount-1; row >=0 ; row--){
        if (operator == null) {
          operator = grid[row][col];
        } else {
          if (accumulator == null) {
            accumulator = Long.valueOf(grid[row][col]);
          } else {
            if (operator.equals("+")) {
              accumulator = accumulator + Long.parseLong(grid[row][col]);
            }  else if (operator.equals("*")) {
              accumulator = accumulator * Long.parseLong(grid[row][col]);
            }
          }
        }
      }
      System.out.println("accumulator: " + accumulator);
      total = total.add(new BigInteger(String.valueOf(accumulator)));
    }
    System.out.println("total: " + total);
  }
}
