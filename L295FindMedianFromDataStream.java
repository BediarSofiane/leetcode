import java.util.Collections;
import java.util.PriorityQueue;
//Link: https://leetcode.com/problems/find-median-from-data-stream/ 
class MedianFinder {
    PriorityQueue<Integer> leftHeap = new PriorityQueue(Collections.reverseOrder());
    PriorityQueue<Integer> rightHeap = new PriorityQueue();
    public MedianFinder() {

    }
    
    public void addNum(int num) {
        int rightSize = rightHeap.size();
        int leftSize = leftHeap.size();
        if(leftSize <= rightSize){
            if(rightHeap.isEmpty() || num <= rightHeap.peek()){
                leftHeap.add(num);
            } else {
                leftHeap.add(rightHeap.poll());
                rightHeap.add(num);
            }
        } else {
            //leftSize > rightSize
            if(num >= leftHeap.peek()){
                rightHeap.add(num);
            } else {
                rightHeap.add(leftHeap.poll());
                leftHeap.add(num);
            }
        }
    }
    
    public double findMedian() {
        int rightSize = rightHeap.size();
        int leftSize = leftHeap.size();
        if(rightSize == leftSize){
            return (double)(leftHeap.peek() + rightHeap.peek()) / 2;
        } else {
            if(rightSize > leftSize){
                return rightHeap.peek();
            } else{
                return leftHeap.peek();
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */