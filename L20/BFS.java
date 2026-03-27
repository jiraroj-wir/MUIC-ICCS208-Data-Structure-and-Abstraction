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
            if (!this.adj.containsKey(v)) { // the vertex doesn't exist
                return 0;
            }

            return this.adj.get(v).size();
        }

        @Override
        public Iterable<Vertex> adj(Vertex v) {
            if (!this.adj.containsKey(v)) {
                return Collections.emptySet();
            }

            return this.adj.get(v);
        }

        @Override
        public boolean isEdge(Vertex u, Vertex v) {
            if (!this.adj.containsKey(u)) {
                return false;
            }

            return this.adj.get(u).contains(v);
        }

        @Override
        public void addVertex(Vertex v) {
            if (!this.adj.containsKey(v)) {
                this.adj.put(v, new HashSet<>());
                this.vertices++;
            }
        }

        @Override
        public void addEdge(Vertex u, Vertex v) {
            addVertex(u);
            addVertex(v);

            if (!this.adj.get(u).contains(v)) {
                this.adj.get(u).add(v);
                this.adj.get(v).add(u);
                this.edges++;
            }
        }

        @Override
        public void removeVertex(Vertex v) {
            if (!this.adj.containsKey(v)) {
                return;
            }

            for (Vertex neighbor : this.adj.get(v)) {
                this.adj.get(neighbor).remove(v);
                this.edges--;
            }

            this.adj.remove(v);
            this.vertices--;
        }
    }

    /*
    2. Given an undirected graph and a pair of nodes, write a method static void findShortest(UndirectedGraph<Integer>
    G, Integer a, Integer b) that prints out the shortest path from vertex a to vertex b.

    findShortest(G, 2, 1) should print 2 3 1

    (Notice that the path starts from a and ends at b)
    */
}
