import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents a weighted graph.
 *
 * @param <V> The type of data held by the vertices.
 */
public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    /**
     * Constructs a weighted graph.
     *
     * @param directed If true, the graph is directed. Otherwise, it is undirected.
     */
    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    /**
     * Returns the map of vertices in the graph.
     *
     * @return The map of vertices.
     */
    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }

    /**
     * Returns the vertex corresponding to the given data.
     *
     * @param data The data of the vertex to retrieve.
     * @return The vertex corresponding to the data, or null if not found.
     */
    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param data The data of the vertex to add.
     */
    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }

    /**
     * Adds an edge between two vertices with the given weight.
     *
     * @param source The source vertex.
     * @param dest The destination vertex.
     * @param weight The weight of the edge.
     */
    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = getOrCreate(source);
        Vertex<V> destVertex = getOrCreate(dest);

        sourceVertex.addAdjacentVertex(destVertex, weight);

        if (!directed)
            destVertex.addAdjacentVertex(sourceVertex, weight);
    }

    /**
     * Returns the vertex corresponding to the given data, creating it if it does not exist.
     *
     * @param data The data of the vertex to retrieve or create.
     * @return The vertex corresponding to the data.
     */
    private Vertex<V> getOrCreate(V data) {
        Vertex<V> vertex = vertices.get(data);
        if (vertex == null) {
            vertex = new Vertex<>(data);
            vertices.put(data, vertex);
        }

        return vertex;
    }

    /**
     * Returns a list of adjacent vertices for the given vertex.
     *
     * @param v The vertex to get adjacent vertices for.
     * @return A list of adjacent vertices.
     */
    public List<Vertex<V>> adjacencyList(V v) {
        Vertex<V> vertex = getOrCreate(v);
        return new LinkedList<>(vertex.getAdjacentVertices().keySet());
    }

    /**
     * Returns an iterable of edges for the given vertex.
     *
     * @param v The vertex to get edges for.
     * @return An iterable of edges.
     */
    public Iterable<Map.Entry<Vertex<V>, Double>> getEdges(V v) {
        Vertex<V> vertex = getOrCreate(v);
        return vertex.getAdjacentVertices().entrySet();
    }
}