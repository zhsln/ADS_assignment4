import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implements Dijkstra's algorithm for finding the shortest paths in a weighted graph.
 *
 * @param <V> The type of data held by the vertices.
 */
public class DijkstraSearch<V> extends Search<V> {
    private final Set<V> unsettledNodes;
    private final Map<V, Double> distances;

    /**
     * Constructs a Dijkstra search algorithm for the given graph starting from the source vertex.
     *
     * @param graph The weighted graph to search.
     * @param source The source vertex to start the search from.
     */
    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(graph, source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();

        dijkstra();
    }

    /**
     * Performs Dijkstra's algorithm to find the shortest paths from the source vertex.
     */
    private void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            V currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (Vertex<V> neighbor : graph.adjacencyList(currentNode)) {
                double newDistance = getShortestDistance(currentNode) + getDistance(currentNode, neighbor.getData());

                if (getShortestDistance(neighbor.getData()) > newDistance) {
                    distances.put(neighbor.getData(), newDistance);
                    edgeTo.put(neighbor.getData(), currentNode);
                    unsettledNodes.add(neighbor.getData());
                }
            }
        }
    }

    /**
     * Returns the distance between two vertices.
     *
     * @param node The starting vertex.
     * @param target The target vertex.
     * @throws RuntimeException If nothing is found.
     * @return The distance between the two vertices.
     */
    private double getDistance(V node, V target) {
        for (Map.Entry<Vertex<V>, Double> entry : graph.getEdges(node)) {
            if (entry.getKey().getData().equals(target)) {
                return entry.getValue();
            }
        }
        throw new RuntimeException("Not found!");
    }

    /**
     * Returns the vertex with the minimum weight from the given set of vertices.
     *
     * @param vertices The set of vertices to check.
     * @return The vertex with the minimum weight.
     */
    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    /**
     * Returns the shortest distance to the given destination vertex.
     *
     * @param destination The destination vertex.
     * @return The shortest distance to the destination vertex.
     */
    private double getShortestDistance(V destination) {
        Double distance = distances.get(destination);
        return (distance == null ? Double.MAX_VALUE : distance);
    }
}