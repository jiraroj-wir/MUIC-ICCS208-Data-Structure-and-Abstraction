import java.util.*;

public class BFS {
    /*
    1. Using the adjancency map representation you've read about in your self-study lesson, write a class class
    UndirectedAdjMap<Vertex> that implements the UndirectedGraph<Vertex> interface (see the reading).
    */

    public interface UndirectedGraph<Vertex> {
        int numEdges();    // How many edges?
        int numVertices(); // How many vertices?
        int deg(Vertex v); // Return the degree of v

        // Return an iterable of vertices adjacent to v
        Iterable<Vertex> adj(Vertex v);

        // Is there an edge between u and v?
        boolean isEdge(Vertex u, Vertex v);

        // Add a vertex
        void addVertex(Vertex v);

        // Add an undirected edge (u -- v)
        void addEdge(Vertex u, Vertex v);

        // Remove a vertex and all incident edges
        void removeVertex(Vertex v);

        // Remove an edge
        void removeEdge(Vertex u, Vertex v);
    }

    class UndirectedAdjMap<Vertex> implements UndirectedGraph<Vertex> {
        private int edges;
        private int vertices;
        private Map<Vertex, Set<Vertex>> adj;

        UndirectedAdjMap() {
            this.edges = 0;
            this.vertices = 0;
            this.adj = new HashMap<>();
        }

        @Override
        public int numEdges() {
            return this.edges;
        }

        @Override
        public int numVertices() {
            return this.vertices;
        }

        @Override
        public int deg(Vertex v) {
            if (!adj.containsKey(v)) { // the vertex doesn't exist
                return 0;
            }

            return adj.get(v).size();
        }

        @Override
        public Iterable<Vertex> adj(Vertex v) {
            if (!adj.containsKey(v)) {
                return Collections.emptySet();
            }

            return adj.get(v);
        }
    }

    /*
    2. Given an undirected graph and a pair of nodes, write a method static void findShortest(UndirectedGraph<Integer>
    G, Integer a, Integer b) that prints out the shortest path from vertex a to vertex b.

    findShortest(G, 2, 1) should print 2 3 1

    (Notice that the path starts from a and ends at b)
    */
}
