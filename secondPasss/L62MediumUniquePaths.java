package secondPasss;

public class L62MediumUniquePaths {
    //Strategy: BackTrack
    //Intuition: the idea is to find all possible paths from 0,0 to m-1, n-1 by moving only right and down. 
    // Start 
    // -> If you can move right, move right and continue, backtrack 
    // -> if you can move down move down and continue 
    // ->  backtrack and return 
    // -> increment counter when you  reach the buttom right cell 
    // -> end
    //Time: O(m*n)
    //Spece: O(m*n)

    int uniquePaths = 0;
    public int uniquePaths(int m, int n) {
        //explore(m, n, 0, 0);
        //return uniquePaths;
        //return recurse(m, n);
        return dynamic(m, n);
    }
    //Backtracking
    private void explore(int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            uniquePaths++;
            return;
        }
        if (i + 1 < m) {
            explore(m, n, i + 1, j);
        }
        if (j + 1 < n) {
            explore(m, n, i, j + 1);
        }
    }

    //Recursion
    public int recurse(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        return recurse(m - 1, n) + recurse(m, n - 1);
    }
    
    private int dynamic(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            matrix[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }
    
}
