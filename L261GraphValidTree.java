import java.util.ArrayList;
import java.util.List;

class L261GraphValidTree {
    // Intuition:
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> neighbors = new ArrayList();
        boolean path[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            neighbors.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] < edges[i][1]) {
                neighbors.get(edges[i][0]).add(edges[i][1]);
            } else {
                neighbors.get(edges[i][1]).add(edges[i][0]);
            }
        }
        System.out.println(neighbors.toString());

        for(int i=0; i<n; i++){
            if(!path[i] && !dfs(0, neighbors, path)) {
            return false;
        }
        }
        return true;
    }

    //Return true if no cyle found in path, and false if a cycle is found.
    private boolean dfs(int node, List<List<Integer>> neighbors, boolean[] path) {
        path[node] = true;
        for (int neighbor : neighbors.get(node)) {
            if (path[neighbor] || !dfs(neighbor, neighbors, path)) {
                return false;
            }
        }
        return true;
    }
}
