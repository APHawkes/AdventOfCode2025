package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Day09A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    List<Coordinate> coordinates = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      StringTokenizer tokenizer = new StringTokenizer(line, ",");
      coordinates.add(new Coordinate(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
    }

    System.out.println(coordinates);

    long maxArea = 0;
    for (Coordinate coordinate1 : coordinates) {
      for (Coordinate coordinate2 : coordinates) {
        long area = (long) (Math.abs(coordinate1.x - coordinate2.x) + 1) * (Math.abs(coordinate1.y - coordinate2.y) + 1);
        maxArea = Math.max(area, maxArea);
      }
    }
    System.out.println(maxArea);
  }

  record Coordinate(int x, int y) {}
}
