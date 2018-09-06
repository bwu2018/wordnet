import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

/**
 * WordNet groups words into sets of synonyms called synsets and describes semantic relationships
 * between them. One such relationship is the is-a relationship, which connects a hyponym (more
 * specific synset) to a hypernym (more general synset). For example, animal is a hypernym of both
 * bird and fish; bird is a hypernym of eagle, pigeon, and seagull.
 *
 * @author bwu2018
 *
 */
public class WordNet {

    /**
     * SAP structure.
     */
    private SAP sap;

    /**
     * Map that has nouns and their ids.
     */
    private final HashMap<String, ArrayList<Integer>> map;

    /**
     * Digraph created from hypernyms.
     */
    private Digraph graph;

    /**
     * Lines of input file.
     */
    private ArrayList<String> lines;

    /**
     * Constructs from name of two input files.
     *
     * @param synsets
     *            sysnset file
     * @param hypernyms
     *            hypernym file
     */
    // Constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {

        // parse through synsets and create data structures
        map = new HashMap<String, ArrayList<Integer>>();
        In in1 = new In(synsets);
        int count = 0;
        lines = new ArrayList<String>();
        while (in1.hasNextLine()) {
            count++;
            String currentLine = in1.readLine();
            lines.add(currentLine);
            String[] split = currentLine.split(",");

            // get id
            int id = Integer.parseInt(split[0]);

            // get synset
            String[] synset = split[1].split(" ");
            for (String noun : synset) {
                if (!map.containsKey(noun)) {
                    ArrayList<Integer> ids = new ArrayList<Integer>();
                    ids.add(id);
                    map.put(noun, ids);
                } else {
                    map.get(noun).add(id);
                }
            }
        }

        // parse through hypernyms and create digraph
        In in2 = new In(hypernyms);
        graph = new Digraph(count);

        while (in2.hasNextLine()) {
            String line = in2.readLine();
            String[] holder = line.split(",");
            for (int i = 1; i < holder.length; i++) {
                graph.addEdge(Integer.parseInt(holder[0]), Integer.parseInt(holder[i]));
            }
        }

        DirectedCycle cycle = new DirectedCycle(graph);
        if (cycle.hasCycle() || !rootedDAG(graph)) {
            throw new IllegalArgumentException();
        }

        sap = new SAP(graph);
    }

    /**
     * Checks if given digraph is rooted DAG or not.
     *
     * @param G
     *            a digraph
     * @return True if is a DAG, false if it isn't.
     */
    private boolean rootedDAG(Digraph G) {
        int roots = 0;
        for (int i = 0; i < G.V(); i++) {
            if (!G.adj(i).iterator().hasNext()) {
                roots++;
                if (roots > 1) {
                    return false;
                }
            }
        }

        return roots == 1;
    }

    /**
     * Returns all WordNet nouns.
     *
     * @return all WordNet nouns
     */
    public Iterable<String> nouns() {
        return map.keySet();
    }

    /**
     * Checks if word is a WordNet noun.
     *
     * @param word
     *            a word
     * @return true if word is a noun and false if it isn't
     */
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return map.containsKey(word);
    }

    /**
     * Calculates distance from nounA to nounB.
     *
     * @param nounA
     *            a noun
     * @param nounB
     *            a noun
     * @return distnace from nounA to nounB
     */
    public int distance(String nounA, String nounB) {
        return sap.length(map.get(nounA), map.get(nounB));
    }

    /**
     * Returns a synset that is the common ancestor of nounA and nounB in a shortest ancestral
     * path.
     *
     * @param nounA
     *            a noun
     * @param nounB
     *            a noun
     * @return a synset that is the common ancestor of nounA and nounB in a shortest ancestral path
     */
    public String sap(String nounA, String nounB) {
        SAP sap = new SAP(graph);
        int id = sap.ancestor(map.get(nounA), map.get(nounB));
        String testLine = lines.get(id);
        String[] testSplit = testLine.split(",");
        return testSplit[1];
    }

    /**
     * Unit testing.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        WordNet test = new WordNet("wordnet-test-files/synsets3.txt",
                "wordnet-test-files/hypernyms3InvalidTwoRoots.txt");
        Iterator<String> nouns = test.nouns().iterator();
        while (nouns.hasNext()) {
            System.out.println(nouns.next());
        }
        System.out.println(test.graph);
        System.out.println(test.sap("b", "d"));
    }
}
