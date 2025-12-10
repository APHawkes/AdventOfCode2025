package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day01A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    int position = 50;
    int zeroCounter = 0;
    while ((line = br.readLine()) != null) {
      StringTokenizer tokenizer = new StringTokenizer(line, " ");
      while (tokenizer.hasMoreTokens()) {
        String token = tokenizer.nextToken();
        int rotate =  Integer.parseInt(token.substring(1));
        if (token.startsWith("L")) {
          rotate = -rotate;
        }
        System.out.println(rotate);
        position += rotate;
        while (position < 0) {
          position += 100;
        }
        while (position >= 100) {
          position -= 100;
        }
        if (position == 0) {
          zeroCounter++;
        }
        System.out.println("new position: " + position);
      }
      System.out.println("Zeroes: " + zeroCounter);
    }
  }
}
