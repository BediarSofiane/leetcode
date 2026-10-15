//Here I will try all sorting algorithms
class L912SortAnArray {
    public int[] sortArray(int[] nums) {
        //quickSort(nums, 0, nums.length - 1);
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        return nums;
    }
    
    //Intuition: select a pivot. put all elements that are smaller than pivot to the left and all elements that are greater than pivot to its right. 
    public void quickSort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int pivot = nums[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (nums[i] <= pivot) {
                i++;
            } else if (nums[j] > pivot) {
                j--;
            } else {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        if (j != left) {
            swap(nums, left, j);
        }
        quickSort(nums, left, j - 1);
        quickSort(nums, j + 1, right);
    }
      
    public void mergeSort(int[] nums, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(nums, left, middle, tmp);
        mergeSort(nums, middle + 1, right, tmp);
        merge(nums, left, middle, right, tmp);
    }
    
    private void merge(int[] nums, int left, int middle, int right, int[] tmp) {
        int i = left;
        int j = middle + 1;
        int k =left;
        while (i <= middle && j <= right) {
            if (nums[i] <= nums[j]) {
                tmp[k++] = nums[i];
                i++;
            } else {
                tmp[k++] = nums[j];
                j++;

            }
        }
        while (i <= middle) {
            tmp[k++] = nums[i++];
        }
        while (j <= right) {
            tmp[k++] = nums[j++];
        }
        k=left;
        for (int x = left; x <= right; x++) {
            nums[x] = tmp[k++];
        }

    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}