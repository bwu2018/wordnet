import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

/**
 * Data structure used in wordnet.
 * @author bwu2018
 *
 */
public class SAP {

    /** A digraph. */
    private Digraph G;

    /**
     * Constructs using a digraph.
     * @param G
     *      a digraph
     */
    public SAP(Digraph G) {
        // SAP is immutable
        this.G = new Digraph(G);
    }

    /**
     * Calculates length of shortest ancestral path between v and w; -1 if no such path.
     *
     * @param v
     *            a vertex in the digraph
     * @param w
     *            a vertex in the digraph
     * @return length of shortest ancestral path between v and w
     */
    public int length(int v, int w) {
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(G, w);
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                if (bfdpv.distTo(i) + bfdpw.distTo(i) < minDistance) {
                    minDistance = bfdpv.distTo(i) + bfdpw.distTo(i);
                }
            }
        }
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }

    /**
     * Returns a common ancestor of v and w that participates in a shortest ancestral path; -1 if no
     * such path.
     *
     * @param v
     *            a vertex in the digraph
     * @param w
     *            a vertex in the digraph
     * @return a common ancestor of v and w that participates in a shortest ancestral path; -1 if no
     *         such path
     */
    public int ancestor(int v, int w) {
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(G, w);
        int minDistance = Integer.MAX_VALUE;
        int minAncestor = -1;
        for (int i = 0; i < G.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                if (bfdpv.distTo(i) + bfdpw.distTo(i) < minDistance) {
                    minDistance = bfdpv.distTo(i) + bfdpw.distTo(i);
                    minAncestor = i;
                }
            }
        }
        return minAncestor;
    }

    /**
     * Calculates length of shortest ancestral path between any vertex in v and any vertex in w; -1
     * if no such path a vertex in the digraph.
     *
     * @param v
     *            a vertex in the digraph
     * @param w
     *            a vertex in the digraph
     * @return length of shortest ancestral path between v and w, -1 if no such path
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(G, w);
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < G.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                if (bfdpv.distTo(i) + bfdpw.distTo(i) < minDistance) {
                    minDistance = bfdpv.distTo(i) + bfdpw.distTo(i);
                }
            }
        }
        if (minDistance == Integer.MAX_VALUE) {
            return -1;
        }
        return minDistance;
    }

    /**
     * Returns a common ancestor of v and w that participates in a shortest ancestral path; -1 if no
     * such path.
     *
     * @param v
     *            a vertex in the digraph
     * @param w
     *            a vertex in the digraph
     * @return a common ancestor of v and w that participates in a shortest ancestral path; -1 if no
     *         such path
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(G, w);
        int minDistance = Integer.MAX_VALUE;
        int minAncestor = -1;
        for (int i = 0; i < G.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                if (bfdpv.distTo(i) + bfdpw.distTo(i) < minDistance) {
                    minDistance = bfdpv.distTo(i) + bfdpw.distTo(i);
                    minAncestor = i;
                }
            }
        }
        return minAncestor;
    }

    /**
     * Unit testing.
     *
     * @param args
     *      command line arguments
     *
     */
    public static void main(String[] args) {
        In in = new In("wordnet-test-files/digraph2.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        System.out.println("Length to common ancestor: " + sap.length(3, 1));
        System.out.println("Common Ancestor: " + sap.ancestor(3, 1));
        // while (!StdIn.isEmpty()) {
        // int v = StdIn.readInt();
        // int w = StdIn.readInt();
        // int length = sap.length(v, w);
        // int ancestor = sap.ancestor(v, w);
        // StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        // }
    }
}
