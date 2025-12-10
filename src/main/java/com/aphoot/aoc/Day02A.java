package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day02A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    long total = 0;
    while ((line = br.readLine()) != null) {
      StringTokenizer tokenizer = new StringTokenizer(line, ",");
      while (tokenizer.hasMoreTokens()) {
        var token = tokenizer.nextToken();
        var start = Long.parseLong(token.substring(0, token.indexOf('-')));
        var end = Long.parseLong(token.substring(token.indexOf('-') + 1));
        System.out.println(start + "," + end);
        total += invalidSum(start,end);
      }
    }
    System.out.println("Total sum is: " + total);
  }

  public static long invalidSum(long start, long end) {
    long sum = 0;
    for (long i = start; i <= end; i++) {
      System.out.println("Evaluating: " + i);
      String numberString = Long.toString(i);

      if (numberString.length()%2 == 1)
        continue;

      //evaluate
      var prefix = numberString.substring(0, numberString.length()/2);
      var suffix = numberString.substring(numberString.length()/2);
      if (prefix.equals(suffix)) {
        System.out.println("Found: " + i);
        sum += i;
      }
    }
    return sum;
  }
}
