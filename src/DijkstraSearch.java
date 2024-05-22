import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<V> unsettledNodes;
    private final Map<V, Double> distances;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(graph, source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();

        dijkstra();
    }

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

    private double getDistance(V node, V target) {
        for (Map.Entry<Vertex<V>, Double> entry : graph.getEdges(node))
            if (entry.getKey().getData().equals(target))
                return entry.getValue();

        throw new RuntimeException("Not found!");
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices)
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum))
                minimum = vertex;

        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double distance = distances.get(destination);

        return (distance == null ? Double.MAX_VALUE : distance);
    }
}