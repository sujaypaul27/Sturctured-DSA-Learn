import java.util.*;

public class DijkstraAlgorithm {
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] shortestPath(int V, List<List<int[]>> adj, int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Node(src, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;
            if (curr.distance > dist[u]) continue;
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }
}
