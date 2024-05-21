import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = getOrCreate(source);
        Vertex<V> destVertex = getOrCreate(dest);

        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (!directed)
            destVertex.addAdjacentVertex(sourceVertex, weight);
    }

    private Vertex<V> getOrCreate(V data) {
        Vertex<V> vertex = vertices.get(data);
        if (vertex == null) {
            vertex = new Vertex<>(data);
            vertices.put(data, vertex);
        }

        return vertex;
    }
}
