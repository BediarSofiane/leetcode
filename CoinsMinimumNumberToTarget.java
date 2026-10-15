import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * This problem can be seen in two ways: 
 *  - Either by writing the problem into a combination of subproblems. (Recursion).
 *      - For this solution you need to (order important):
 *          1. Identifying all the sub problems WHAT IS A SUBPROBLEM, WHAT ARE ALL SUBPROBLEMS
 *          2. Identify the way to combine them to solve the bigger problem. HOW TO COMBINE SUBPROBLEMS TO GET THE FINAL PROBLEM.
 *          3. Identify the base case(S) for the recursion
 *          4. write the recursion relaction in this form:
 *              {
 *              Solution (Problem) = F(Solution(SubProblem1), Solution(SubProblem2), ..., Solution(SubProblemN)), where F is the function that combines all SubProblems
 *              Solution(Base case 1) = 
 *              Soution( Base Case 2) =
 *              .
 *              .
 *              .
 *              Solution(Base Case n) =
 *              } 
 *          5. Ask yourself whether you want to solve the problem in a TOP_DOWN way or in a BOTTOM_UP way. You will be in one of the two following situations:
 *              1. YOU HAVE THE CHOICE (TOP_DOWN & BOTTOM_UP are equivalent in terms of Time and Space complexity):
 *                  If you notice that their are some OVERLAPING CASES = cases that you will be computed multiple times if their result is not stored.
 *                  This mean that you have to use a storage solution to redice your time complexity. So you can go either ways.
 *              2. YOU DON'T HAVE THE CHOICE:
 *                  If you notice that their are NO OVERLAPING CASES -> This means that going TOP_DOWN will be more efficient because you will not need to store intermediate results like in BOTTOM_UP.
 *                  BUT ! THEIR CAN BE A CASE WHERE YOU CHOOSE BOTTOM_UP EVEN IN CASE FO NON OVERLAPING CASES : WHEN RECURSION HAS THE RISK OF CONSUMING ALL STACK SPACE.
 *              3. SOME TIMES IT'S EASIER AND QUICKER TO THINK BOTTOM_UP THAN TOP_DOWN DEPENDING ON THE PROBLEM. MORE ON THIS LATER.
 *          5. Start coding by creating your recursive function as follows:
 *              1. Write the base cases that should be simple. (Make sure you identified and coded all base cases).
 *              2. Write the recursion function F after the base cases.
 *              3. While writing the recursion function, you will notice that you will need some parameters that should come from the calling function (coins and the new target for this example).
 *          6. Call your recursive function with the right initial parameters in your main method.
 *          7. You have your first version of the solution. Now you will need to optimize it. How to do that:
 *          8
 *  - Or by building the tree of possibilities at each step and stoping when we reach the first solution. (BFS)
 */

public class CoinsMinimumNumberToTarget {
    public int minimumNumberToTarget(int[] coins, int target){
        Integer result = null;

        Queue<List<Integer>>  queue = new LinkedList<>();
        for (int i = 0; i < coins.length; i++) {
            List<Integer> child = new ArrayList<>();
            child.add(coins[i]);
            child.add(1);
            queue.offer(child);
        }  
        return process(coins, target, queue);
    }

    public int process(int[] coins, int target, Queue<List<Integer>> queue){
        //find all possiblities of choosing a coin at every position of the tree 
        // process every possibility in BFS
        // return immediately when you find the first solution that gets you to target
        Set<List<Integer>> memory = new HashSet<>();
        
        while (!queue.isEmpty()) {
            List<Integer> node = queue.poll();
            if(memory.contains(node)){
                continue;
            }
            
            if(node.get(0) == target){
                return node.get(1);
            }
            if(node.get(0) > target){
                continue;
            }
            memory.add(node);
            for(int i=0; i<coins.length; i++){
                List<Integer> child = new ArrayList<>();
                child.add(node.get(0) + coins[i]);
                child.add(node.get(1) + 1);
                queue.offer(child); 
            }
            
        }
        return 0;
    }
}
