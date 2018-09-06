import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {

    private SAP sapDigraph1;
    private SAP sapDigraphTest;
    private SAP sapDigraph2;
    private SAP sapDigraphWordnet;

    @Before
    public void setup() {
        In in = new In("wordnet-test-files/digraph1.txt");
        Digraph G = new Digraph(in);
        System.out.println(G);
        sapDigraph1 = new SAP(G);
        
        In in1 = new In("wordnet-test-files/digraph2.txt");
        Digraph G1 = new Digraph(in1);
        System.out.println(G1);
        sapDigraph2 = new SAP(G1);
        
        In in2 = new In("wordnet-test-files/digraph-wordnet.txt");
        Digraph G2 = new Digraph(in2);
        System.out.println(G2);
        sapDigraphWordnet = new SAP(G2);
    }

    @Test
    public void testSAPImmutable() {
        In in = new In("wordnet-test-files/digraph1.txt");
        In in1 = new In("wordnet-test-files/digraph2.txt");
        Digraph G = new Digraph(in);
        sapDigraphTest = new SAP(G);
        assertEquals(4, sapDigraphTest.length(3, 11));
        G = new Digraph(in1);
        assertEquals(4, sapDigraphTest.length(3, 11));
    }

    @Test
    public void testLengthIntInt() {
        assertEquals(4, sapDigraph1.length(3, 11));
    }
    
    @Test
    public void testLengthIntInt2() {
        assertEquals(2, sapDigraph2.length(2, 4));
    }
    
    @Test
    public void testLengthIntIntWordnet() {
        assertEquals(2, sapDigraphWordnet.length(5, 9));
    }

    @Test
    public void testAncestorIntInt() {
        assertEquals(1, sapDigraph1.ancestor(3, 11));
    }
    
    @Test
    public void testAncestorIntInt2() {
        assertEquals(4, sapDigraph2.ancestor(2, 4));
    }
    
    @Test
    public void testAncestorIntIntWordnet() {
        assertEquals(34093, sapDigraphWordnet.ancestor(5, 9));
    }

    @Test
    public void testLengthIterableIterable() {
        ArrayList<Integer> verticesV = new ArrayList<Integer>();
        verticesV.add(7);
        verticesV.add(4);
        verticesV.add(9);
        ArrayList<Integer> verticesW = new ArrayList<Integer>();
        verticesW.add(11);
        verticesW.add(2);
        assertEquals(3, sapDigraph1.length(verticesV, verticesW));
    }
    
    @Test
    public void testLengthIterableIterable2() {
        ArrayList<Integer> verticesV = new ArrayList<Integer>();
        verticesV.add(4);
        verticesV.add(3);
        ArrayList<Integer> verticesW = new ArrayList<Integer>();
        verticesW.add(2);
        verticesW.add(1);
        assertEquals(1, sapDigraph2.length(verticesV, verticesW));
    }
    
    @Test
    public void testLengthIterableIterableWordnet() {
        ArrayList<Integer> verticesV = new ArrayList<Integer>();
        verticesV.add(7);
        verticesV.add(4);
        verticesV.add(9);
        ArrayList<Integer> verticesW = new ArrayList<Integer>();
        verticesW.add(11);
        verticesW.add(2);
        assertEquals(2, sapDigraphWordnet.length(verticesV, verticesW));
    }

    @Test
    public void testAncestorIterableIterable() {
        ArrayList<Integer> verticesV = new ArrayList<Integer>();
        verticesV.add(7);
        verticesV.add(4);
        verticesV.add(9);
        ArrayList<Integer> verticesW = new ArrayList<Integer>();
        verticesW.add(11);
        verticesW.add(2);
        assertEquals(0, sapDigraph1.ancestor(verticesV, verticesW));
    }
    
    @Test
    public void testAncestorIterableIterable2() {
        ArrayList<Integer> verticesV = new ArrayList<Integer>();
        verticesV.add(4);
        verticesV.add(5);
        ArrayList<Integer> verticesW = new ArrayList<Integer>();
        verticesW.add(2);
        verticesW.add(1);
        assertEquals(0, sapDigraph2.ancestor(verticesV, verticesW));
    }
    
    @Test
    public void testAncestorIterableIterableWordnet() {
        ArrayList<Integer> verticesV = new ArrayList<Integer>();
        verticesV.add(7);
        verticesV.add(4);
        verticesV.add(9);
        ArrayList<Integer> verticesW = new ArrayList<Integer>();
        verticesW.add(11);
        verticesW.add(2);
        assertEquals(34093, sapDigraphWordnet.ancestor(verticesV, verticesW));
    }
}
