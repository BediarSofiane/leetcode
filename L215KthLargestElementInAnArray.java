import java.util.PriorityQueue;

class L215KthLargestElementInAnArray {
    // Appraoch: MinHeap
    // Intuition: Use a Minheap of size K in which we store all elements untill heap
    // is full. Once heap full, we compare each remaining element with heap's head,
    // and only add if its higher. Return the head of the heap.
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        while (i < k) {
            pq.add(nums[i]);
            i++;
        }
        while (i < nums.length) {
            if (nums[i] > pq.peek()) {
                pq.poll();
                pq.add(nums[i]);
            }
            i++;
        }
        return pq.peek();

    }
}