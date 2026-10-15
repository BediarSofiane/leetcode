import java.util.List;
import java.util.ArrayList;

/***
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * Link: https://leetcode.com/problems/generate-parentheses/
 */
class L22GenerateParentheses {
    // add a simple main to demonstrate allValidCombinations (moved to top)
    public static void main(String[] args) {
        int n = 3; // sample input
        L22GenerateParentheses sol = new L22GenerateParentheses();
        List<String> combinations = new ArrayList<>();
        sol.allValidCombinations(combinations, n, "", 0, 0);
        System.out.println("n = " + n);
        System.out.println("combinations = " + combinations);
    }

    // This is my second try. This is not the optimal solution, but the simplest
    // solution to get by thinking in a backtracking tree of possibilities. How is
    // that? you start by generating all possibilities from the head of the tree,
    // then you identify what makes you stop going deeper (in this case, we stop
    // when we reach the target size (2*n) or when we reach an invalid state. then
    // you just add the code that make you add all possibilites to your current
    // node.
    // Template is:
    // 1- start with a empty head of you tree of possibilites
    // 2- generate all possibilites to start
    // 3- Identify what makes a branch stop: Two things make a branch stop: either a
    // final state, or an invalide state. create you conditions for both of these
    // stopping criteria and put them as the first thing to be executed of your
    // recursive method.
    // 4- just make sure to go through all the possibilities for generating the next
    // node. (all the branches to the children). by calling the recursive method as
    // many times as their are children with the right parameters.
    // 5- Try to optimize the solution by looking at
    private void allValidCombinations(List<String> result, int n, String s, int open, int close) {
        // Stop if #close > #open
        // Strop if sting size is > n
        if (open < close || open + close > 2 * n) {
            return;
        }
        if (open == close && open + close == 2 * n) {
            result.add(s);
            return;
        }
        if (open + close < 2 * n) {

        }
        allValidCombinations(result, n, s + "(", open + 1, close);
        allValidCombinations(result, n, s + ")", open, close + 1);
    }

    // This is the improved optimized version of the previous one. To get to this
    // version from the previous one, you just need to try avoiding extra calls when
    // you know that it will need to a failed solution anyway.For instance, in this
    // case, when you know that by calling the next recursive call you will end up
    // with open < close or open + close > 2*n, then you just don't do the call.
    // Time: O(2**n)
    // Space: O(n)
    private void compute(List<String> combinations, int open, int closed, int n, String current) {
        if (open + closed == 2 * n) {
            combinations.add(current);
            return;
        }
        if (open < n) {
            compute(combinations, open + 1, closed, n, current + "(");
        }
        if (closed < open) {
            compute(combinations, open, closed + 1, n, current + ")");
        }
    }
}