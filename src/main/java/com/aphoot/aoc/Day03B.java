package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day03B {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    long total=0;
    while ((line = br.readLine()) != null) {
      StringTokenizer tokenizer = new StringTokenizer(line, " ");
      while (tokenizer.hasMoreTokens()) {
        String token = tokenizer.nextToken();

        var largest = getLargest(token, 12);
        System.out.println("largest: "+largest);
        total+=largest;
      }
      System.out.println("total: "+total);
    }
  }

  private static long getLargest(String token, int pickCount) {
    int remaining = pickCount;
    int lastSelectedIndex = 0;
    String accumulator = "";

    while (remaining > 0) {
      int largestIndex = -1;
      int largestValue = -1;
      for(int i = lastSelectedIndex; i < token.length()-remaining+1; i++) {
        var digit = Integer.parseInt(token.substring(i, i+1));
        if (digit > largestValue) {
          largestIndex = i;
          largestValue = digit;
        }
      }
      lastSelectedIndex = largestIndex+1;
      accumulator += token.substring(largestIndex, largestIndex+1);
      remaining--;
    }
    return Long.parseLong(accumulator);
  }
}

