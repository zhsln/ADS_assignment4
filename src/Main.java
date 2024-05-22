public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>(true);
        fillWithWeights(weightedGraph);

        System.out.println("Dijkstra:");
        Search<String> djk = new DijkstraSearch<>(weightedGraph, "Almaty");
        outputPath(djk, "Kyzylorda");

        System.out.println("--------------------------------");

        WeightedGraph<String> graph = new WeightedGraph<>(true);
        fillWithoutWeights(graph);

        System.out.println("BFS:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, "Almaty");
        outputPath(bfs, "Kyzylorda");
    }

    public static void fillWithoutWeights(WeightedGraph<String> graph) {
        // I know that there are weights, but they are the same. I added this method because of the GitHub example.
        graph.addEdge("Almaty", "Astana", 1.0);
        graph.addEdge("Shymkent", "Atyrau", 1.0);
        graph.addEdge("Atyrau", "Astana", 1.0);
        graph.addEdge("Almaty", "Shymkent", 1.0);
        graph.addEdge("Shymkent", "Astana", 1.0);
        graph.addEdge("Astana", "Kostanay", 1.0);
        graph.addEdge("Shymkent", "Kyzylorda", 1.0);
    }

    public static void fillWithWeights(WeightedGraph<String> graph) {
        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Shymkent", "Atyrau", 7.8);
        graph.addEdge("Atyrau", "Astana", 7.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.9);
        graph.addEdge("Astana", "Kostanay", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
    }

    public static void outputPath(Search<String> search, String key) {
        Iterable<String> path = search.pathTo(key);
        if (path == null) {
            System.out.println("No path found to " + key);
        } else {
            for (String v : path) {
                System.out.print(v + " -> ");
            }
            System.out.println();
        }
    }
}