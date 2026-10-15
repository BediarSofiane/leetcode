class L167TwoSumll {

    // Intuition: Find the first element that verifies the condition target -
    // element < element using binary search. then suppose this element is the
    // second part of the sum and try finding the first part in the first half of
    // the array. If not found, try supposing the next element as the second part
    // and looking for the first part in the same array. until finding the solution
    // Time: O(logn) for first binary search + (n/2 * logn) for each supposed second
    // half = O(nlogn)
    // Space: O(1)
    public int[] twoSumBinarySearch(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0;
        int right = n - 1;
        int candidate = n + 1;
        while (left <= right) {
            int middle = (left + right) / 2;

            if (target - numbers[middle] == numbers[middle]) {
                candidate = middle;
                left = middle + 1;
            } else if (target - numbers[middle] < numbers[middle]) {
                candidate = Math.min(candidate, middle);
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        int i = candidate;
        while (i < n) {
            int secondCandiate = numbers[i];
            int firstCandiate = target - secondCandiate;
            int newLeft = 0;
            int newRight = candidate - 1;
            while (newLeft <= newRight) {
                int newMiddle = (newLeft + newRight) / 2;
                if (numbers[newMiddle] == firstCandiate) {
                    return new int[] { newMiddle + 1, i + 1 };
                } else if (numbers[newMiddle] < firstCandiate) {
                    newLeft = newMiddle + 1;
                } else {
                    newRight = newMiddle - 1;
                }
            }
            i++;
        }
        return new int[] { -1, -1 };
    }

    // Intuition: The key information is that the array is sorted.
    // 1. Using 2 indices, one moving from the end to the beginning and the other in
    // reverse direction.
    // 2. While the two indices does not cross, verify the sum of elements at these
    // indices. If it's equal to target return result, else, move the one that gets
    // the sum closer to target.
    // Time: O(logn) for first binary search + (n/2 * logn) for each supposed second
    // half = O(n)
    // Space: O(1)
    public int[] twoSumTwoPointers(int[] numbers, int target) {
        int n = numbers.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] { -1, -1 };
    }

}