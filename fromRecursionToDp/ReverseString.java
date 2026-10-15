package fromRecursionToDp;

public class ReverseString {
    public String revString(String s) {
        return revStringRecursive(s);
    }

    //Time: O(n) -> n being the number of chars in the string
    //Space: O(n/2) -> stack has n/2 calls because we reduce by 2 the size of the string each time.
    private String revStringRecursive(String s) {
        if (s.length() <= 1) {
            return s;
        }
        return s.charAt(s.length() - 1) + revStringRecursive(s.substring(1, s.length() - 1)) + s.charAt(0);
    }

    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        String res = reverseString.revString("sofiane");
        System.out.println(res);
    }
}
