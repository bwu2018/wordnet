import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Finds the outcast, the word farthest from the others, from a wordnet object.
 * @author bwu2018
 *
 */
public class Outcast {

    /**
     * Wordnet object.
     */
    private WordNet wordnet;

    /**
     * Constructor takes a WordNet object.
     * @param wordnet wordnet object
     */
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    /**
     * Given an array of WordNet nouns, return an outcast.
     * @param nouns
     *      string of nouns
     * @return word that is the farthest away from others
     */
    public String outcast(String[] nouns) {
        int largestDistance = 0;
        String outcast = new String();
        for (String x : nouns) {
            int distance = 0;
            for (String y : nouns) {
                distance += wordnet.distance(x, y);
            }
            if (distance > largestDistance) {
                largestDistance = distance;
                outcast = x;
            }
        }
        return outcast;
    }

    /**
     * Unit testing.
     * @param args
     *      command line arguments
     */
    // see test client below
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
