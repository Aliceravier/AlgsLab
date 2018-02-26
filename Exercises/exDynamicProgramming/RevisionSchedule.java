import java.util.HashMap;

public class RevisionSchedule {

    // Spoiler alert! Hints at the bottom of the file.

    // Difficulty without spoilers: hard
    // Estimated time without spoilers: 2h

    // Difficulty with spoilers: easy
    // Estimated time with spoilers: 1h

    // Adam is trying to schedule a k-day exam revision. On each day he's going to either:

    // Rest (R)
    // Study Maths (M)
    // Study Computing (C)
    // Adam never studies two different subjects on two consecutive days. In how many ways can Adam schedule his revision?

    // Assuming that k = 2, there are 7 possible revision schedules: RR RM RC MR MM CR CC. Note: MC is not a valid schedule, because at one point in the schedule a subject (M) is immediately followed by another subject (C).

    // You can define additional methods in this class.


    public static long countSchedulesNaive(int k) {
        // Please provide a naive, (mutually) recursive solution to the problem.
        return 0L;
    }

    public static long countSchedulesEfficient(int k, HashMap<Long, Long> lookup) {
        // Please provide an efficient solution to the problem using either dynamic programming (you can just ignore `lookup`), or recursive call caching (memoisation) using `lookup`.
        
        return 0L;
    }

    public static void test(boolean beQuiet) {
        if (!beQuiet) {
            System.out.println("error!");
        }
    }

    public static void main(String[] args) {
        System.out.println(
                "You're testing ChangeMaking.java. If a bug is detected you'll be notified. If test cases fail to terminate you should use more efficient programming techniques.");

        test(countSchedulesNaive(2) == 7);
        test(countSchedulesNaive(1) == 3);
        test(countSchedulesNaive(20) == 54608393);

        //System.out.println(countSchedulesNaive(20));

        // Test case is too slow for most unoptimised solutions.
        // System.out.print(countSchedulesNaive(30));

        test(countSchedulesEfficient(2, new HashMap<Long, Long>()) == 7);
        test(countSchedulesEfficient(1, new HashMap<Long, Long>()) == 3);
        test(countSchedulesEfficient(20, new HashMap<Long, Long>()) == 54608393);
        test(countSchedulesEfficient(42, new HashMap<Long, Long>()) == 14398739476117879L);
        test(countSchedulesEfficient(40, new HashMap<Long, Long>()) == 2470433131948081L);
    }
}

// Hints

// One elegant way to solve the task is to use mutual recursion (but there are other ways). You can define three helper functions R, K, and M, as follows (using python):

/*
def R(k):
    if k == 1:
        return 1
    else:
        return R(k-1) + M(k-1) + C(k-1)

def M(k):
    if k == 1:
        return 1
    else:
        return R(k-1) + M(k-1)

def C(k):
    if k == 1:
        return 1
    else:
        return R(k-1) + C(k-1)
*/

// The solution for a number of possible schedules in a k-day revison is: R(k) + M(k) + C(k)

/*
Why this works:

1. Each (partial) valid schedule for k days ending in a given way (R, M, C) can be produced by extending another (partial) valid schedule for (k - 1) days, according to the following production rules:
"...R" -> "...RR"
"...R" -> "...RM"
"...R" -> "...RC"
"...M" -> "...MR"
"...M" -> "...MM"
"...C" -> "...CR"
"...C" -> "...CC"
2. In this way, each schedule is counted exactly once.
3. Therefore, the number of schdules for k days ending with R, is exactly the number of schedules for (k - 1) days ending with R, M or C, as shown by the production rules "...R" -> "...RR", "...M" -> "...MR", "...C" -> "...CR"
4. Therefore, to get a total number of k-day schedules ending in R, you can just sum up (k-1)-day schedules ending in R, M or C
5. You can work out recursive relations for other schedules ending with M or C from the production rules.
6. When we're building our 1-day schedule (base case), we can use either R, M or C, that's why R(1) == M(1) == C(1) == 
*/

// Made with <3 by Adam Kurkiewicz