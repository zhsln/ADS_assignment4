import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        super(graph, source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, V current) {
        marked.add(current);
        Queue<V> queue = new LinkedList<>();
        queue.add(current);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Vertex<V> vertex : graph.getVertex(v).getAdjacentVertices().keySet())
                if (!marked.contains(vertex.getData())) {
                    marked.add(vertex.getData());
                    edgeTo.put(vertex.getData(), v);
                    queue.add(vertex.getData());
                }
        }
    }
}
