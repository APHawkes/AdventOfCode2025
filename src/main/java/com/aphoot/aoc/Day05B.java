package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Day05B {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    boolean collecting = true;
    Set<Range> ranges = new HashSet<>();
    while ((line = br.readLine()) != null) {
      if (line.isEmpty()) {
        collecting = false;
        continue;
      }
      if (collecting) {
        StringTokenizer tokenizer = new StringTokenizer(line, "-");
        while (tokenizer.hasMoreTokens()) {
          ranges.add(new Range(Long.parseLong(tokenizer.nextToken()), Long.parseLong(tokenizer.nextToken())));
        }
      }
    }

    //handle overlaps
    boolean updated = true;
    REDO:
    while (updated){
      updated = false;
      for (Range range : ranges) {
        for (Range range2 : ranges) {
          if (range.equals(range2)) continue;

          if (range.start() <= range2.end() && range.end() >= range2.start()) {
            updated = true;
            ranges.remove(range);
            ranges.remove(range2);
            long start = Math.min(range.start(), range2.start());
            long end = Math.max(range.end(), range2.end());
            ranges.add(new Range(start, end));
            continue REDO;
          }
        }
      }
    }
    //Add sizes of all ranges
    var x = (Long) ranges.stream()
        .map(r -> r.end() - r.start() + 1)
        .mapToLong(Long::longValue)
        .sum();
    System.out.println(x);
  }

  record Range(long start, long end) {}
}
