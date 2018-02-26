import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class GoogleWordBreak {

    // Difficulty: hard
    // Estimated time: 2h

    // Given a dictionary of posible words and a single string, please determine if the string can be split into a sequence of words from the dictionary. Dictionary can be very large, so please use it *only* for containment checks, which should be efficient given any reasonable implementation of the Map interface. Please do not iterate over the dictionary. Return an array of words or `null` if the task can't be accomplished.

    // Examples: with string "adrenaline" and dictionary {"ad", "line", "adrenaline"} the correct answer would be the array ["adrenaline"].
    // With string "crabrow", and dictionary {"crab", "brow", "row"} -> ["crab", "row"].
    // With string "aaa", and dictionary {"a", "aa"} -> ["a", "aa"] or ["aa", "a"] or ["a", "a", "a"], any of these would be a valid solution.

    // This problem was originally discussed by a senior software engineer & a former Google employee on his blog:
    // http://thenoisychannel.com/2011/08/08/retiring-a-great-interview-problem

    // Please approach the blog post with care, as it presents only cached/memoised solution, without discussing a dynamic programming solution, which might be a more natural/ elegant way of solving the problem. Also, storing the lookup table as a global variable is a very bad idea, as subsequent calls to the word-breaking method will interfere with the previous calls producing potentially incorrect results.

    public static String[] breakWords(String words, Set<String> dictionary, Map<String, String[]> lookup) {
        // Please provide an efficient implementation of the solution to the problem using caching/memoisation or dynamic programming. If using dynamic programming, you can just ignore the `lookup` parameter.

        return null;
    }

    public static void test(String word, String[] dictionary, boolean possible) {
        StringBuilder sb = new StringBuilder();
        String[] wordbreak = breakWords(word, new HashSet<>(Arrays.asList(dictionary)), new HashMap<String, String[]>());
        boolean testResult;
        if (possible) {
            for (String s : wordbreak) {
                sb.append(s);
            }
            testResult = sb.toString().equals(word);
        } else {
            testResult = wordbreak == null; 
        }
        if (!testResult) {
            System.out.println("failure");
        }
    }

    public static void main(String[] args) {
        System.out.println(
                "You're testing GoogleWordBreak.java. If a bug is detected you'll be notified. If test cases fail to terminate you should use more efficient programming techniques.");
        test("adamlikesmuffins", new String[]{"adam", "likes", "muffins"}, true);
        test("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new String[]{"a", "aa", "aaa"}, false);
        test("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new String[]{"a", "aa", "aaa"}, false);
        test("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", new String[]{"a", "aa", "aaa"}, false);
        test("crabrow", new String[]{"crab", "brow", "row"}, true);
        test("adrenaline", new String[]{"ad", "line", "adrenaline"}, true);
        test("aaa", new String[]{"a", "aa", "adrenaline"}, true);
    }

}

// Made with <3 by Adam Kurkiewicz