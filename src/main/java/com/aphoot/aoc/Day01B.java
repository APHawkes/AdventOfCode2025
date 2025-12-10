package com.aphoot.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Day01B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int position = 50;
        int zeroCounter = 0;
        while ((line = br.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreTokens()) {
                System.out.println("----");
                String token = tokenizer.nextToken();
                int rotate = Integer.parseInt(token.substring(1));
                if (token.startsWith("L")) {
                    rotate = -rotate;
                }
                System.out.println(rotate);

                while (rotate != 0) {
                  if (rotate > 0) {//right
                    int distanceToZero = 100-position;
                    if (rotate >= distanceToZero) {
                      rotate -= distanceToZero;
                      position = 0;
                      zeroCounter++;
                    } else {
                      position = position + rotate;
                      rotate = 0;
                    }
                  } else { //left
                    int distanceToZero = position==0 ? 100 : position;
                    if (rotate <= -distanceToZero) {
                      rotate = rotate + distanceToZero;
                      position = 0;
                      zeroCounter++;
                    } else {
                      position = position + rotate%100;
                      position = position < 0 ? position+100 : position;
                      rotate = 0;
                    }
                  }
                }
                System.out.println("new position: " + position);
            }
            System.out.println("Zeroes: " + zeroCounter);
        }
    }
}
