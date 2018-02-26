import java.util.HashMap;
import java.util.Arrays;

public class ChangeMaking {
    // Difficulty: moderate
    // Estimated time: 1h

    // Please provide two efficient implementations of the change-making algorithm: given a set of possible coins, for example [1, 2, 5], produce a change of a given sum, for example 13. Acceptable answers would be [5, 5, 2, 1] or [2, 2, 2, 2, 2, 2, 1] or similar, as long as the sum of change is equal to the original amount. If no change can be given, please return null.

    public static int[] makeChangeDynamic(int amount, int[] coins) {
        // Use dynamic programming to implement the algorithm. There is a python solution provided in the lecture notes (available on moodle), which you can use as a guideline for providing your own Java solution.

        return 0L;
    }

    public static int[] makeChangeCaching(int amount, int[] coins, int[] partialSolution,
            HashMap<Integer, int[]> lookup) {
        // Use caching/memoisation to implement the algorithm. There is a python solution provided in the lecture notes (available on moodle), which you can use as a guideline for providing your own Java solution.

        return null;
    }

    public static boolean testChange(int[] args, int sum) {
        int accumulator = 0;
        for (int i = 0; i < args.length; i++) {
            accumulator += args[i];
        }
        return sum == accumulator;
    }

    public static boolean testCaching(int amount, int[] coins, boolean possible) {
        int[] result = makeChangeCaching(amount, coins, new int[] {}, new HashMap<Integer, int[]>());
        boolean test;
        if (possible) {
            test = testChange(result, amount);
        } else {
            test = null == result;
        }
        if (!test) {
            System.out.println("failure!");
        }
        return test;
    }

    public static boolean testDynamic(int amount, int[] coins, boolean possible) {
        int[] result = makeChangeDynamic(amount, coins);
        boolean test;
        if (possible) {
            test = testChange(result, amount);
        } else {
            test = null == result;
        }
        if (!test) {
            System.out.println("error!");
        }
        return test;
    }

    public static void main(String[] args) {
        System.out.println(
                "You're testing ChangeMaking.java. If a bug is detected you'll be notified. If test cases fail to terminate you should use more efficient programming techniques.");

        testCaching(7, new int[] { 1, 2, 5 }, true);
        testCaching(96, new int[] { 11, 38, 27 }, false);
        testCaching(97, new int[] { 11, 38, 27 }, false);
        testCaching(98, new int[] { 11, 38, 27 }, true);
        testCaching(99, new int[] { 11, 38, 27 }, true);
        testCaching(100, new int[] { 11, 38, 27 }, false);
        testCaching(101, new int[] { 11, 38, 27 }, false);
        testCaching(102, new int[] { 11, 38, 27 }, false);
        testCaching(103, new int[] { 11, 38, 27 }, true);
        testCaching(0, new int[] { 11, 38, 27 }, true);
        testCaching(1000, new int[] { 3, 6, 9, 12 }, false);
        testCaching(1000, new int[] { 3, 6, 9, 13 }, true);

        testDynamic(7, new int[] { 1, 2, 5 }, true);
        testDynamic(96, new int[] { 11, 38, 27 }, false);
        testDynamic(97, new int[] { 11, 38, 27 }, false);
        testDynamic(98, new int[] { 11, 38, 27 }, true);
        testDynamic(99, new int[] { 11, 38, 27 }, true);
        testDynamic(100, new int[] { 11, 38, 27 }, false);
        testDynamic(101, new int[] { 11, 38, 27 }, false);
        testDynamic(102, new int[] { 11, 38, 27 }, false);
        testDynamic(103, new int[] { 11, 38, 27 }, true);
        testDynamic(0, new int[] { 11, 38, 27 }, true);
        testDynamic(1000, new int[] { 3, 6, 9, 12 }, false);
        testDynamic(1000, new int[] { 3, 6, 9, 13 }, true);
    }

}

// Made with <3 by Adam Kurkiewicz
