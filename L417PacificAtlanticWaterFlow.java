import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class L417PacificAtlanticWaterFlow {
    // Intuition: initialize two 2D matrices canGoToPacific and canGoToAtlantic of
    // boolean values. for each matrice, initialize the bordering row and column
    // with true values for the corresponding ocean matrix, then go through the
    // original grid, and check for each cell, if it can or not go to the given
    // ocean using by checking the 4 possible directions.
    //Time: O(n*m)
    //Space: O(n*m)
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] canGoToAtlantic = new boolean[m][n];
        boolean[][] canGoToPacific = new boolean[m][n];
        List<List<Integer>> result = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (!canGoToPacific[0][j]) {
                canGoToPacific[0][j] = true;
                visit(heights, m, n, canGoToPacific, 0, j);
            }
            if (!canGoToAtlantic[m-1][j]) {
                canGoToAtlantic[m-1][j] = true;
                visit(heights, m, n, canGoToAtlantic, m-1, j);
            }
          
        }
        for (int i = 0; i < m; i++) {
            if (!canGoToPacific[i][0]) {
                canGoToPacific[i][0] = true;
                visit(heights, m, n, canGoToPacific, i, 0);
            }
            if (!canGoToAtlantic[i][n - 1]) {
                canGoToAtlantic[i][n - 1] = true;
                visit(heights, m, n, canGoToAtlantic, i, n - 1);
            }

        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canGoToAtlantic[i][j] && canGoToPacific[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result; 
    }

    private void visit(int[][] heights, int m, int n, boolean[][] canGoToOcean, int i, int j) {
        if (i - 1 >= 0 && !canGoToOcean[i - 1][j] && heights[i - 1][j] >= heights[i][j]) {
            canGoToOcean[i - 1][j] = true;
            visit(heights, m, n, canGoToOcean, i - 1, j);
        }
        if (i + 1 < m && !canGoToOcean[i + 1][j] && heights[i + 1][j] >= heights[i][j]) {
            canGoToOcean[i + 1][j] = true;
            visit(heights, m, n, canGoToOcean, i + 1, j);
        }
        if (j - 1 >= 0 && !canGoToOcean[i][j - 1] && heights[i][j - 1] >= heights[i][j]) {
            canGoToOcean[i][j - 1] = true;
            visit(heights, m, n, canGoToOcean, i, j - 1);
        }
        if (j + 1 < n && !canGoToOcean[i][j + 1] && heights[i][j + 1] >= heights[i][j]) {
            canGoToOcean[i][j + 1] = true;
            visit(heights, m, n, canGoToOcean, i, j + 1);
        }
    }
    

}