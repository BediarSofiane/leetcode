class L11ContainerWithMostWater {
    //Intuition: Start with two pointers starting from opposite directions. compute the area, compare it to the max so far, then move the pointer with the smallest value toward the other. If both values are equal, move both of them. keep the track of the biggest smallest value, if you reach a new value that is smaller or equal than the biggest smallest one, just skip it. otherwise recompute the new area, and take the max area.
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int maxArea = 0;
        int maxSmall = 0;
        while (left < right) {
            if (height[left] <= maxSmall) {
                left++;
                continue;
            }
            if (height[right] <= maxSmall) {
                right--;
                continue;
            }
            int newArea = 0;
            if (height[left] < height[right]) {
                newArea = height[left] * (right - left);
                maxSmall = Math.max(maxSmall, height[left]);
                left++;
            } else if (height[left] > height[right]) {
                newArea = height[right] * (right - left);
                maxSmall = Math.max(maxSmall, height[right]);
                right--;
            } else {
                newArea = height[right] * (right - left);
                maxSmall = Math.max(maxSmall, height[right]);
                left++;
                right--;
            }
            maxArea = Math.max(maxArea, newArea);
        }
        return maxArea;
    }
}