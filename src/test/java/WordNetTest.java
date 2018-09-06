import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class WordNetTest {

    private WordNet wordNet6TwoAncestors;
    private WordNet wordNet;

    @Before
    public void setUp() throws Exception {
        wordNet6TwoAncestors = new WordNet("wordnet-test-files/synsets6.txt",
                "wordnet-test-files/hypernyms6TwoAncestors.txt");
        wordNet = new WordNet("wordnet-test-files/synsets.txt", "wordnet-test-files/hypernyms.txt");
    }

    @Test(timeout = 5000, expected = IllegalArgumentException.class)
    public void testWordNetNullSysnets() {
        WordNet w = new WordNet(null, "wordnet-test-files/hypernyms.txt");
    }

    @Test(timeout = 5000, expected = IllegalArgumentException.class)
    public void testWordNetNullHypernyms() {
        WordNet w = new WordNet("wordnet-test-files/sysnets.txt", null);
    }

    @Test(timeout = 5000, expected = IllegalArgumentException.class)
    public void hasCycle() {
        WordNet w = new WordNet("wordnet-test-files/synsets3.txt",
                "wordnet-test-files/hypernyms3InvalidCycle.txt");
    }

    @Test(timeout = 5000, expected = IllegalArgumentException.class)
    public void hasTwoRoots() {
        WordNet w = new WordNet("wordnet-test-files/synsets3.txt",
                "wordnet-test-files/hypernyms3InvalidTwoRoots.txt");
    }

    @Test
    public void testNouns() {
        String[] expectedNounsArray = { "a", "b", "c", "d", "e", "f" };
        ArrayList<String> expectedNouns = new ArrayList<String>(Arrays.asList(expectedNounsArray));
        for (String actualNoun : wordNet6TwoAncestors.nouns()) {
            assertTrue(expectedNouns.contains(actualNoun));
        }

    }

    @Test
    public void testNouns3() {
        WordNet w = new WordNet("wordnet-test-files/synsets3.txt",
                "wordnet-test-files/hypernyms3.txt");
        String[] expectedNounsArray = { "a", "b", "c" };
        ArrayList<String> expectedNouns = new ArrayList<String>(Arrays.asList(expectedNounsArray));
        for (String actualNoun : w.nouns()) {
            assertTrue(expectedNouns.contains(actualNoun));
        }
    }

    @Test
    public void testNoun15() {
        WordNet w = new WordNet("wordnet-test-files/synsets15.txt",
                "wordnet-test-files/hypernyms15Tree.txt");
        String[] expectedNounsArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o" };
        ArrayList<String> expectedNouns = new ArrayList<String>(Arrays.asList(expectedNounsArray));
        for (String actualNoun : w.nouns()) {
            assertTrue(expectedNouns.contains(actualNoun));
        }
    }

    @Test
    public void testIsNoun() {
        assertTrue(wordNet6TwoAncestors.isNoun("a"));
    }

    @Test
    public void testFrontIsNoun() {
        assertTrue(wordNet.isNoun("'hood"));
    }

    @Test
    public void testMiddleIsNoun() {
        assertTrue(wordNet.isNoun("highbrow"));
    }

    @Test
    public void testEndIsNoun() {
        assertTrue(wordNet.isNoun("zymosis"));
    }

    @Test
    public void testIsNounFalse() {
        assertFalse(wordNet6TwoAncestors.isNoun("g"));
    }

    public void testIsNounFalseClose() {
        assertFalse(wordNet.isNoun("zmology"));
    }

    public void testIsNounJumbled() {
        assertFalse(wordNet.isNoun("oaiwejfiowjeoj"));
    }

    public void testIsNounNull() {
        assertFalse(wordNet.isNoun(""));
    }

    @Test
    public void testDistance() {
        assertEquals(3, wordNet6TwoAncestors.distance("e", "b"));
    }

    @Test
    public void testDistance11() {
        WordNet w = new WordNet("wordnet-test-files/synsets11.txt",
                "wordnet-test-files/hypernyms11AmbiguousAncestor.txt");
        assertEquals(1, w.distance("i", "j"));
        assertEquals(3, w.distance("b", "g"));
    }

    @Test
    public void testDistanceManyPaths() {
        WordNet w = new WordNet("wordnet-test-files/synsets11.txt",
                "wordnet-test-files/hypernymsManyPathsOneAncestor.txt");
        assertEquals(2, w.distance("i", "j"));
        assertEquals(2, w.distance("b", "g"));
    }

    @Test
    public void testSap() {
        assertEquals("a", wordNet6TwoAncestors.sap("e", "b"));
    }

    @Test
    public void testSap11() {
        WordNet w = new WordNet("wordnet-test-files/synsets11.txt",
                "wordnet-test-files/hypernyms11AmbiguousAncestor.txt");
        assertEquals("c", w.sap("e", "b"));
        assertEquals("c", w.sap("c", "b"));
    }

    @Test
    public void testSapManyPaths() {
        WordNet w = new WordNet("wordnet-test-files/synsets11.txt",
                "wordnet-test-files/hypernymsManyPathsOneAncestor.txt");
        assertEquals("f", w.sap("e", "b"));
        assertEquals("f", w.sap("c", "b"));

    }

}
