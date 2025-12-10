package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day02B {
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

      //divide numberString into multiple divisions
      for(int patternLength = 1; patternLength < numberString.length(); patternLength++) {
        if (numberString.length()%patternLength != 0)
          continue;

        //evaluate
        var pattern = numberString.substring(0, patternLength);
        var patternMatches = true;
        for(int patternIndex = 0; patternIndex < numberString.length()/patternLength; patternIndex++) {
          var evaluation = numberString.substring(patternIndex*patternLength,(patternIndex+1)*patternLength);
          if(!evaluation.equals(pattern)) {
            patternMatches = false;
            break;
          }
        }
        if(patternMatches) {
          System.out.println("Found pattern in: " + i);
          sum += i;
          break; //pattern found, no need to keep searching for patterns
        }
      }

    }
    return sum;
  }
}
