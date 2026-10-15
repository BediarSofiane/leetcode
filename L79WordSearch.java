class L79WordSearch {
    // Intuition: Go over the matrix elements, if the character is different than
    // the first character of the word, keep going, otherwise, start visiting the
    // neighbors horizontally and vertically in a dfs mode, visit only the
    // characters that make the word continue, stop at two conditions, either you
    // reached the end of the word or no more way to keep constructing the word.
    //Time: O(n*m*4^word.length())
  public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        //Using visited matrix was the first naive solution before optimization.
        //boolean[][] visited = new boolean[m][n];
        boolean found = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //An optimiztion would be to not use visited matrix, and directly change in the original board;
                //found = backTrack(board, word, visited, i, j,0);
                found = backTrack(board, word, i, j,0);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTrack(char[][] board, String word, boolean[][] visited, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || visited[i][j]) {
            return false;
        }
        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        visited[i][j] = true;
        boolean found = backTrack(board, word, visited, i, j + 1, index + 1) ||
                backTrack(board, word, visited, i, j - 1, index + 1) ||
                backTrack(board, word, visited, i + 1, j, index + 1) ||
                backTrack(board, word, visited, i - 1, j, index + 1);
        visited[i][j] = false;
        return found;
    }
    
    private boolean backTrack(char[][] board, String word, int i, int j, int index) {
        if (index == word.length()) {
            return true;
        }
        if(i>= board.length || i< 0 || j>= board[0].length || j< 0){
            return false;
        }
        if (word.charAt(index) != board[i][j]) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = '$';
        boolean found = backTrack(board, word, i, j+1, index+1) || 
                        backTrack(board, word, i, j-1, index+1) ||
                        backTrack(board, word, i+1, j, index+1) ||
                        backTrack(board, word, i-1, j, index+1);
        board[i][j] = tmp;
        return found;
    }
}