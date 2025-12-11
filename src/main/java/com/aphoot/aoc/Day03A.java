package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day03A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    int total=0;
    while ((line = br.readLine()) != null) {
      StringTokenizer tokenizer = new StringTokenizer(line, " ");
      while (tokenizer.hasMoreTokens()) {
        int largest = 0;
        String token = tokenizer.nextToken();
        System.out.println(token);
        for(int i=0; i<token.length()-1; i++) {
          String digit1 = token.substring(i, i+1);
          for(int j=i+1; j<token.length(); j++) {
            String digit2 = token.substring(j, j+1);
            System.out.println(digit1+digit2);
            int current = Integer.parseInt(digit1+digit2);
            if(current > largest) {
              largest = current;
            }
          }
        }
        System.out.println("largest: "+largest);
        total+=largest;
      }
      System.out.println("total: "+total);
    }
  }
}
