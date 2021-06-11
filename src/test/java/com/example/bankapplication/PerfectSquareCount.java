package com.example.bankapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class PerfectSquareCount {
    static int countSquares(int l, int b)
    {
        if (b < l)
        {
            // swap(m, n)
            int temp = l;
            l = b;
            b = temp;
        }

        return l * (l + 1) * (2 * l + 1) /
                6 + (b - l) * l * (l + 1) / 2;
    }

    public static void main(String[] args)
    {
        System.out.println("Input");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        List<int[]> testcases = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            int[] lb = Arrays.stream(s.split(" +")).flatMapToInt(num -> IntStream.of(Integer.parseInt(num))).toArray();
            testcases.add(lb);
        }

        System.out.println("Output");
        for (int i = 0; i < n; i++) {
            System.out.println(countSquares(testcases.get(i)[0], testcases.get(i)[1]));
        }

        in.close();
    }
}
