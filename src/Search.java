import java.util.*;

/**
 * Abstract class for search algorithms in a weighted graph.
 *
 * @param <V> The type of data held by the vertices.
 */
public class Search<V> {
    protected Set<V> marked;
    protected Map<V, V> edgeTo;
    protected final V source;
    protected final WeightedGraph<V> graph;

    /**
     * Constructs a search algorithm for the given graph starting from the source vertex.
     *
     * @param graph The weighted graph to search.
     * @param source The source vertex to start the search from.
     */
    public Search(WeightedGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    /**
     * Checks if there is a path to the given vertex.
     *
     * @param v The vertex to check for a path.
     * @return True if there is a path to the vertex, false otherwise.
     */
    public boolean hasPathTo(V v) {
        return marked.contains(v);
    }

    /**
     * Returns an iterable representing the path to the given vertex.
     *
     * @param v The vertex to get the path to.
     * @return An iterable representing the path to the vertex, or null if no path exists.
     */
    public Iterable<V> pathTo(V v) {
        if (!hasPathTo(v))
            return null;

        LinkedList<V> linkedList = new LinkedList<>();
        for (V i = v; !i.equals(source); i = edgeTo.get(i))
            linkedList.push(i);

        linkedList.push(source);

        return linkedList;
    }
}