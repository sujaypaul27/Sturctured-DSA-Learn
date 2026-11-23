import java.util.*;

public class TopologicalSortKahnAlgorithm {
    public static int[] topologicalSort(int numNodes, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            adj.add(new ArrayList<>());
        }
        int[] indegree = new int[numNodes];
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] topoOrder = new int[numNodes];
        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topoOrder[index++] = node;
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        if (index != numNodes) {
            return new int[0];
        }
        return topoOrder;
    }
}
