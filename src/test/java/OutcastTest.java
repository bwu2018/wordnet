import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.In;

public class OutcastTest {

    private WordNet wordNet;
    private Outcast outcast;

    @Before
    public void setUp() throws Exception {
        wordNet = new WordNet("wordnet-test-files/synsets.txt", "wordnet-test-files/hypernyms.txt");
        outcast = new Outcast(wordNet);
    }

    @Test
    public void testOutcast() {
        In in = new In("wordnet-test-files/outcast10.txt");
        String[] string = new String[10];
        for (int i = 0; i < 10; i++) {
            string[i] = in.readString();
        }
        assertEquals("albatross", outcast.outcast(string));
    }

    @Test
    public void testOutcast1() {
        In in = new In("wordnet-test-files/outcast29.txt");
        String[] string = new String[29];
        for (int i = 0; i < 29; i++) {
            string[i] = in.readString();
        }
        assertEquals("acorn", outcast.outcast(string));
    }

    @Test
    public void testOutcast2() {
        In in = new In("wordnet-test-files/outcast11.txt");
        String[] string = new String[11];
        for (int i = 0; i < 11; i++) {
            string[i] = in.readString();
        }
        assertEquals("potato", outcast.outcast(string));
    }

}
