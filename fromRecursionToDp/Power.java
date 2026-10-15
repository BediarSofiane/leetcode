package fromRecursionToDp;

public class Power {

    public double power(int x, int n) {
        //return n < 0 ? 1 / powerLinear(x, -n) : powerLinear(x, n);
        return n < 0 ? 1.0 / powerLog(x, -n) : powerLog(x, n);
    }

    //Recursion contract: 
    // returns: x to the power of n
    // assumptions: the result of x to the power of n/2 is correct
    // base case: x to the power of 0 is equal to 1
    // transition: let y= x to the power of n/2, return y*y if n is even, and y*y*x if n is odd.
    //Time: O(logn)
    //Space: O(logn)
    private long powerLog(int x, int n) {
        if (n == 0)
            return 1;
        long half = powerLog(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }

    //Recursion contract: 
    // returns: x to the power of n
    // assumptions: the result of x to the power of n-1 is correct
    // base case: x to the power of 0 is equal to 1
    // transition: let y= x to the power of n-1, return x*y
    //Time: O(n)
    //Space: O(n)
    public long powerLinear(int x, int n) {
        if (n == 0)
            return 1;
         if (x == 0) {
            return x;
        }
        return x * powerLinear(x, n - 1);
    }
    
    public static void main(String[] args) {
        Power power = new Power();
        System.out.println(power.power(2, 3));
    }
}
