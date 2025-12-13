package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day06B {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;

    List<String> lines = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      lines.add(line);
    }

    int colCount = lines.stream().map(String::length).reduce(0, Integer::max);
    int rowCount = lines.size();

    BigInteger total = new BigInteger("0");
    char operator = ' ';
    List<Long> operands = new ArrayList<>();
    for(int col = 0; col <= colCount; col++){
      if (operator == ' '){
        operator = safeGetChar(lines.getLast(), col);
        System.out.println("operator: " + operator);
      }

      var columnString = "";
      for(int row = 0; row < rowCount-1; row++){
        columnString += safeGetChar(lines.get(row), col);
      }

      if (columnString.isBlank() || col == colCount) {
        long result;
        if (operator == '+') {
          result = operands.stream().reduce(0L, Long::sum);
        } else { //multiply
          result = operands.stream().reduce(1L, (a,b)->a*b);
        }
        System.out.println("result: " + result);
        total = total.add(new BigInteger(String.valueOf(result)));

        operator = ' ';
        operands.clear();
      } else {
        operands.add(Long.parseLong(columnString.trim()));
      }
    }
    System.out.println("total: " + total);
  }

  private static char safeGetChar(String line, int col) {
    if (col >= line.length()) return ' ';
    return line.charAt(col);
  }
}
