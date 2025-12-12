package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day05A {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    boolean collecting = true;
    int freshCount = 0;
    Set<Range> ranges = new HashSet<>();
    while ((line = br.readLine()) != null) {
      if (line.length() == 0) {
        collecting = false;
        continue;
      }
      if (collecting) {
        StringTokenizer tokenizer = new StringTokenizer(line, "-");
        while (tokenizer.hasMoreTokens()) {
          ranges.add(new Range(Long.parseLong(tokenizer.nextToken()), Long.parseLong(tokenizer.nextToken())));
        }
      } else {
        //testing
        long food = Long.parseLong(line);
        boolean fresh = ranges.stream()
            .anyMatch(x-> x.start() <= food && x.end() >= food);
        if (fresh) {
          freshCount++;
        }
      }
    }
    System.out.println(ranges);
    System.out.println(ranges.size());
    System.out.println(freshCount);
    //660 too low
  }

  record Range(long start, long end) {}
}
