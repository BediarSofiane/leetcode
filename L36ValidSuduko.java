public class L36ValidSuduko {

    // Intuition:
    // 1. Create 3 arrays boolean Rows, Columns, and Boxes. for each array, the case
    // is
    // true if the corresponding digits has already been visited for the
    // corresponding vector.
    // 2. Go through the elements of the matrix, and for each element that is not
    // empty, if it's already visited in one of the arrays, return false. return true if all elements of the matrix have been visited.

    //Time: O(n)
    //Space: O(n)
    public boolean isValidSudoku(char[][] board) {
        
        boolean[][] rows = new boolean[9][10];
        boolean[][] columns = new boolean[9][10];
        boolean[][] boxes = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current = board[i][j];
                if (current == '.') {
                    continue;
                }
                int currentAsInt = current - '0';
                if (rows[i][currentAsInt] || columns[j][currentAsInt] || boxes[i / 3 + 3 * (j / 3)][currentAsInt]) {
                    return false;
                } else {
                    rows[i][currentAsInt] = true;
                    columns[j][currentAsInt] = true;
                    boxes[i / 3 + 3 * (j / 3)][currentAsInt] = true;
                }
            }
        }
        return true;

    }
}
