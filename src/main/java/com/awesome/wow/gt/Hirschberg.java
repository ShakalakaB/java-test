package com.awesome.wow.gt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Hirschberg {

    /**
     * Generic constructor
     */
    public Hirschberg() {

    }

    /**
     * Algorithm B as described by Hirschberg.	 * returns the last line of the Needleman-Wunsch score matrix Score(i,j):
     *
     * @param aLength
     * @param bLength
     * @param a
     * @param b
     * @return
     */
    public int[] algB(int aLength, int bLength, String a, String b) {

        // Step 1
        int[][] k = new int[2][bLength + 1];
        for (int j = 0; j <= bLength; j++) {
            k[1][j] = 0;
        }

        // Step 2
        for (int i = 1; i <= aLength; i++) {
            // Step 3
            for (int j = 0; j <= bLength; j++) {
                k[0][j] = k[1][j];
            }

            // Step 4
            for (int j = 1; j <= bLength; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    k[1][j] = k[0][j - 1] + 1;
                } else {
                    k[1][j] = max(k[1][j - 1], k[0][j]);
                }
            }
        }

        //Step 5
        return k[1];

    }

    /**
     * This method returns the maximum number between two numbers.
     *
     * @param x
     * @param y
     * @return
     */
    public int max(int x, int y) {
        if (x > y) {
            return x;
        } else {
            return y;
        }
    }

    /**
     * Algorithm C as described by Hirschberg
     *
     * @param aLength
     * @param bLength
     * @param a
     * @param b
     * @return
     */
    public String algC(int aLength, int bLength, String a, String b) {
        int aIndex = 0;
        int bIndex = 0;
        String commonSubsequence = "";

        // Step 1
        if (bLength == 0) {
            commonSubsequence = "";
        } else if (aLength == 1) {
            commonSubsequence = "";
            for (bIndex = 0; bIndex < bLength; bIndex++) {
                if (a.charAt(0) == b.charAt(bIndex)) {
                    commonSubsequence = "" + a.charAt(0);
                }
            }

            // Step 2
        } else {
            aIndex = (int) Math.floor(((double) aLength) / 2);

            // Step 3
            int[] l1 = algB(aIndex, bLength, a.substring(0, aIndex), b);
            int[] l2 = algB(aLength - aIndex, bLength, reverseString(a.substring(aIndex)), reverseString(b));

            // Step 4
            int k = findK(l1, l2, bLength);

            // Step 5
            String c1 = algC(aIndex, k, a.substring(0, aIndex), b.substring(0, k));
            String c2 = algC(aLength - aIndex, bLength - k, a.substring(aIndex), b.substring(k));

            commonSubsequence = c1 + c2;
        }

        return commonSubsequence; // The LCS
    }


    /**
     * This method takes a string as input reverses it and
     * returns the result
     *
     * @param in
     * @return
     */
    public String reverseString(String in) {
        String out = "";

        for (int i = in.length() - 1; i >= 0; i--) {
            out = out + in.charAt(i);
        }

        return out;
    }


    /**
     * This method finds the index of the maximum sum of L1 and L2,
     * as described by Hirschberg
     *
     * @param l1
     * @param l2
     * @param n
     * @return
     */
    public int findK(int[] l1, int[] l2, int n) {
        int m = 0;
        int k = 0;

        for (int j = 0; j <= n; j++) {
            int c = l1[j] + l2[n - j];
            if (m < (l1[j] + l2[n - j])) {
                m = l1[j] + l2[n - j];
                k = j;
            }
        }

        return k;
    }


    /**
     * The main method for the algorithm
     *
     * @param args
     */
    public static void main(String[] args) {
//        if (args.length != 2) {
//            System.err.println("Usage: Enter two strings X and Y");
//        } else {
//            String x = "abc";
//            String y = "adb";
//            Hirschberg alg = new Hirschberg();
//            System.out.println("LCS: " + alg.algC(x.length(), y.length(), x, y)); //computes & prints out the result
//        }
        String x = "CASCADE";
        String y = "CADENCE";
        Hirschberg alg = new Hirschberg();
        System.out.println("LCS: " + alg.algC(x.length(), y.length(), x, y)); //computes & prints out the result
    }

}
