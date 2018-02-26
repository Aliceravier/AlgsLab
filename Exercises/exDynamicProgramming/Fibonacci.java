import java.util.HashMap;
import java.util.Map;
public class Fibonacci {
    
    // Difficulty: easy
    // Estimated time: 0.5h
    
    // Fibonacci sequence is an infinite sequence of integers beginning 1, 1, 2, 3, 5, 8, 13, ...
    
    // Fibonacci sequence is defined recursively, with the first two terms defined to be 1,
    // and each subsequent term being a sum of the two terms immediately preceding it.
    
    // Please implement 3 different versions of a program to compute the k-th value of the Fibonacci sequence.
    // e.g. naive_fibonacci(6) == 8


    public static long fibonacciNaive(long k) {
        // Please implement a naive, recursive version of the program.
        return 0L;
    }

    public static long fibonacciCached(long k, Map<Long, Long> lookup) {
        // Please implement an optimsed version of the program using a hash map to cache the results of previously evaluated calls to `fibonacci_cached`.
        return 0L;
    }

    
    public static long fibonacciDynamicProgramming(int k) {
        // Please implement a dynamic programming version of the program using a one-dimensional array to store the results of the computation up to the k-th element of the sequence. Return the kth element.
        return 0L;
    }

    public static void test(boolean beQuiet) {
        if (!beQuiet) {
            System.out.println("error!");
        }
    }

    // Please run the main function to check for some possible bugs.
    public static void main(String args[]) {
        System.out.println("You're testing Fibonacci.java. You will be notified if you fail any test cases. If test cases fail to terminate you should use more efficient programming techniques.");
        test(fibonacciNaive(6) == 8);
        test(fibonacciNaive(10) == 55);
        test(fibonacciNaive(1) == 1);
        test(fibonacciNaive(13) == 233);

        // This test case takes a very long time to compute due to inefficiency of the naive approach.
        //test(fibonacciNaive(50) == 12586269025L);

        test(fibonacciNaive(6) == fibonacciCached(6, new HashMap<Long, Long>()));
        test(fibonacciNaive(10) == fibonacciCached(10, new HashMap<Long, Long>()));
        test(fibonacciNaive(1) == fibonacciCached(1, new HashMap<Long, Long>()));
        test(fibonacciNaive(13) == fibonacciCached(13, new HashMap<Long, Long>()));
        test(fibonacciCached(50, new HashMap<Long, Long>()) == 12586269025L);
        
        test(fibonacciNaive(6) == fibonacciDynamicProgramming(6));
        test(fibonacciNaive(10) == fibonacciDynamicProgramming(10));
        test(fibonacciNaive(1) == fibonacciDynamicProgramming(1));
        test(fibonacciNaive(13) == fibonacciDynamicProgramming(13));
        test(fibonacciDynamicProgramming(50) == 12586269025L);
    }
}

// Made with <3 by Adam Kurkiewicz