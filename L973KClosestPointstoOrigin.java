import java.util.Collections;
import java.util.PriorityQueue;

class L973KClosestPointstoOrigin {
    //Appraoch: MaxHeap
    //Intuition: Go over the points one by one, and put them in a max heap of size k based on their distance to the origin. return the elements of the heap.
    //Time: O(nlogk)
    //Space: O(k)
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k,Collections.reverseOrder((a, b) -> a.distance >= b.distance ? 1: -1));
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int[] point = points[i];
            double distance = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
            if (maxHeap.size() < k) {
                maxHeap.add(new Point(point, distance));

            } else if (distance < maxHeap.peek().distance) {
                maxHeap.poll();
                maxHeap.add(new Point(point, distance));
            }
            
            
        }
        int[][] result = new int[k][];
        int i = 0;
        for (Point p : maxHeap) {
            result[i++] = p.coordinates;
        }
        return result;
    }
}

class Point {
    public int[] coordinates;
    public double distance;

    public Point() {

    }

    public Point(int[] coordinates, double distance) {
        this.coordinates = coordinates;
        this.distance = distance;
    }
}