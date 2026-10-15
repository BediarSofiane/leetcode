class L200NumberOfIslands {
    // Approach: DP
    // Intuition: Go over the grid, when you see a 1 you increment the counter, and
    // start visiting the island starting from the current 1, marking every visited
    // elemnt so it's not visited again. When you reach the end of the grid, the
    // counter will contain the number of islands.
    //Time: O(m*n)
    //Space: O(m*n)
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == false && grid[i][j] == '1') {
                    islands++;
                    visit(grid, visited, i, j);
                }
            }
        }
        return islands;
    }

    private void visit(char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j<0 || j>= grid[0].length || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        visit(grid, visited, i, j + 1);
        visit(grid, visited, i, j - 1);
        visit(grid, visited, i + 1, j);
        visit(grid, visited, i - 1, j);

    }
}