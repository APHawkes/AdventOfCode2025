package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day08A {

  public static final int CONNECTION_COUNT = 1000;
  public static final int CIRCUIT_SAMPLE = 3;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    Set<Box> boxes = new HashSet<>();
    SortedMap<Double, Set<Pair>> pairDistances = new TreeMap<>();
    List<Set<Box>> circuits = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      StringTokenizer tokenizer = new StringTokenizer(line, ",");
      var box = new Box(Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()),Integer.parseInt(tokenizer.nextToken()));
      boxes.add(box);
    }

    //populate pairDistance
    for (Box box : boxes) {
      for  (Box box1 : boxes) {
        if (box.equals(box1)) continue; //no connections to self
        var pair = box.compareTo(box1) > 0 ? new Pair(box, box1) : new Pair(box1, box);
        pairDistances.computeIfAbsent(pair.distance(), k -> new HashSet<>()).add(pair);
      }
    }

    var entrySetIterator = pairDistances.entrySet().iterator();
    for (int i = 0; i < CONNECTION_COUNT; i++) {
      var entry = entrySetIterator.next();
      var pair = entry.getValue().iterator().next();
      var boxA = pair.a();
      var boxB = pair.b();

      //is one if these in a circuit? Add the other!
      var foundBoxA = circuits.stream().filter(x-> x.contains(boxA)).findFirst();
      var foundBoxB = circuits.stream().filter(x-> x.contains(boxB)).findFirst();
      //Both in circuits? combine!
      if (foundBoxA.isPresent() && foundBoxB.isPresent()) {
        if (!foundBoxA.get().equals(foundBoxB.get())) { //If they're already in the SAME circuit, do nothing
          foundBoxA.get().addAll(foundBoxB.get());
          circuits.remove(foundBoxB.get());
        }
      } else if (foundBoxA.isPresent()) {// Only one in a circuit? Add the other!
        foundBoxA.get().add(boxB);
      } else if (foundBoxB.isPresent()) {// Only one in a circuit? Add the other!
        foundBoxB.get().add(boxA);
      } else {//Not in any circuit? make a new one!
        circuits.add(new HashSet<>(List.of(boxA, boxB)));
      }
    }

    //final calculation
    var top = circuits.stream().map(Set::size).sorted((x,y)->-Integer.compare(x,y)).limit(CIRCUIT_SAMPLE);
    var reduction = top.reduce((x,y)->x*y);
    System.out.println(reduction);
  }

  record Box(int x, int y, int z) implements Comparable<Box> {
    @Override
    public int compareTo(Box o) {
      return Comparator.comparingInt(Box::x)
          .thenComparingInt(Box::y)
          .thenComparingInt(Box::z)
          .compare(this, o);
    }
  }

  record Pair(Box a, Box b) {
    double distance(){
      return Math.sqrt(Math.pow(a.x() - b.x(), 2) + Math.pow(a.y() - b.y(), 2) + Math.pow(a.z() - b.z(), 2));
    }
  }
}
