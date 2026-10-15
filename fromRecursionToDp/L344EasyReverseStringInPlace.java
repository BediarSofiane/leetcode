package fromRecursionToDp;

public class L344EasyReverseStringInPlace {
    public void reverseString(char[] s) {
        reverseStringRecursive(s, 0);
    }

    public void reverseStringLinear(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            swap(s, l++, r--);
        }
    }

    // returns: void
    // assumption: the recursive call reverses the substring between start + 1 and
    // end -1 inclusive on both sides.
    // base case: the reversion of an empty string or a single character string is
    // the string itself
    // transition: swap current start and end characters and delegate the chars in
    // between to the recursion
    // Time: O(n/2) recursive calls, each doing O(1) swaps -> O(n)
    // Space: O(n/2) stack calls -> O(n)
    private void reverseStringRecursive(char[] s, int i) {
        int n = s.length;
        if (i >= n / 2)
            return;
        swap(s, i, n - 1 - i);
        reverseStringRecursive(s, i + 1);
    }

    private void swap(char[] s, int start, int end) {
        char tmp = s[end];
        s[end] = s[start];
        s[start] = tmp;
    }

    public static void main(String[] args) {
        L344EasyReverseStringInPlace reverseStringInPlace = new L344EasyReverseStringInPlace();
        char[] input = "abcde".toCharArray();
        reverseStringInPlace.reverseString(input);
        System.out.println(input);

    }
}
