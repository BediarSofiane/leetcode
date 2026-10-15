import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class L207CourseSchedule {
    // Intuition: transform the prerequisites array to a map where each course
    // points to a list to its prerequisites, then start with one of the courses and
    // do BFS on the map to try to find a cycle. Use a visited array of boolean that
    // tells when a node is visited two times during the process.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] globallyVisited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            List<Integer> neighbors = graph.get(prerequisites[i][0]);
            neighbors.add(prerequisites[i][1]);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            if (globallyVisited[prerequisites[i][0]]) {
                continue;
            }
            if (!dfs(prerequisites[i][0], graph, visited, globallyVisited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int course, List<List<Integer>> graph, boolean[] visited, boolean[] globallyVisited) {
        globallyVisited[course] = true;
        List<Integer> neighbors = graph.get(course);
        visited[course] = true;
        for (int neighbor : neighbors) {
            if (visited[neighbor] || !dfs(neighbor, graph, visited, globallyVisited)) {
                return false;
            }
        }
        visited[course] = false;
        return true;
    }

}