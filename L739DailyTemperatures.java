import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class L739DailyTemperatures {
    // Strategy: BFS or QUEUEing
    // Intuition: Go over the elements of the array, for each element, check the
    // queue, while the current element is greater than the head of the queue,
    // dequeue, and update the result of the dequed element. than enqueue current
    // element.
    // Time: O(n)
    // Space: O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> indicesStack = new Stack();
        for (int i = 0; i < n; i++) {
            while (indicesStack.size() > 0 && temperatures[i] > temperatures[indicesStack.peek()]) {
                int head = indicesStack.pop();
                result[head] = i - head;
            }
            indicesStack.add(i);
        }
        return result;
    }
}