import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements BFS for a weighted graph.
 *
 * @param <V> The type of data held by the vertices.
 */
public class BreadthFirstSearch<V> extends Search<V> {

    /**
     * Constructs a BFS algorithm for the given graph starting from the source vertex.
     *
     * @param graph The weighted graph to search.
     * @param source The source vertex to start the search from.
     */
    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        super(graph, source);
        bfs(graph, source);
    }

    /**
     * Performs BFS on the graph starting from the given vertex.
     *
     * @param graph The weighted graph to search.
     * @param current The current vertex to start the search from.
     */
    private void bfs(WeightedGraph<V> graph, V current) {
        marked.add(current);
        Queue<V> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Vertex<V> vertex : graph.getVertex(v).getAdjacentVertices().keySet()) {
                if (!marked.contains(vertex.getData())) {
                    marked.add(vertex.getData());
                    edgeTo.put(vertex.getData(), v);
                    queue.add(vertex.getData());
                }
            }
        }
    }
}