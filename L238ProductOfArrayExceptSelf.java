class L238ProductOfArrayExceptSelf {
    // Intuition:
    // Go through all elements of the array, for each element, start a
    // counter from the beginning of the array and a counter from the end of the
    // array and multiply every element by the result of multiplication so far. make
    // sure to not multiply by the current element.
    // Time: O(n^2)
    // Space : O(1)
    public int[] productExceptSelfBruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 1;
            for (int j = 0; j < i; j++) {
                result[i] *= nums[j];
            }
            for (int k = n - 1; k > i; k--) {
                result[i] *= nums[k];
            }
        }
        return result;
    }

    // Intuition:
    // Compute the product of all elements of the array. for each
    // element, devide the total product by the element itself. The con is division
    // by zero. If you find one zero, then all the other elements will be 0 except
    // the element corresponding to 0 that will be the result of multiplication
    // ignoring the 0, if more than one zero than all elements will be zero.
    // Time: O(n)
    // Space: O(1)
    public int[] productExceptSelfUsingDivision(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int totalProduct = 1;
        int zeroCount = 0;
        int indiceWithZero = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                indiceWithZero = i;
                if (zeroCount > 1) {
                    break;
                }
            } else {
                totalProduct *= nums[i];
            }
        }
        if (zeroCount > 1) {
            return result;
        } else if (zeroCount == 1) {
            result[indiceWithZero] = totalProduct;
        } else {
            for (int i = 0; i < n; i++) {
                result[i] = totalProduct / nums[i];
            }
        }
        return result;
    }

    // Intuition:
    // Create two arrays prefixArray and suffixArray that will contain the prefix
    // and suffix product for each element of the original array. then go over them
    // and just multiply every prefix with every suffix
    // Time: O(n)
    // Space: O(n)
    public int[] productExceptSelfUsingPrefixSuffixArrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] prefixes = new int[n + 1];
        int[] suffixes = new int[n + 1];
        prefixes[0] = 1;
        suffixes[n] = 1;
        
        for (int i = 0; i < n; i++) {
            prefixes[i + 1] = prefixes[i] * nums[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            suffixes[i] = suffixes[i + 1] * nums[i];
        }
        
        for (int i = 0; i < n; i++) {
            result[i] = prefixes[i] * suffixes[i + 1];
        }

        return result;
    }
}