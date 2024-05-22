import java.util.*;

public class Search<V> {
    protected Set<V> marked;
    protected Map<V, V> edgeTo;
    protected final V source;
    protected final WeightedGraph<V> graph;

    public Search(WeightedGraph<V> graph, V source) {
        this.graph = graph;
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(V v) {
        return marked.contains(v);
    }

    public Iterable<V> pathTo(V v) {
        if(!hasPathTo(v))
            return null;

        LinkedList<V> linkedList = new LinkedList<>();
        for (V i = v; !i.equals(source); i = edgeTo.get(i))
            linkedList.push(i);

        linkedList.push(source);

        return linkedList;
    }
}