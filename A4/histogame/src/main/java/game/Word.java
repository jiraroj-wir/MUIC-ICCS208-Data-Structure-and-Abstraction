package game;

import histogram.Histogram;
import histogram.SimpleHistogram;

public class Word implements Formable<Word>, Comparable<Word> {
    private String word;
    private Histogram<Character> hist;

    // The only constructor of the class, which takes a String representation
    // of the word. The histogram is generated here from the given word.
    public Word(String word) {
        this.word = word.toLowerCase(); // case in-sensitive
        this.hist = new SimpleHistogram<>();

        for (char ch : this.word.toCharArray()) {
            hist.setCount(ch, hist.getCount(ch) + 1);
        }
    }

    @Override
    public boolean canForm(Word other) {
        // TODO: Fix me
        return false;
    }

    @Override
    public int compareTo(Word o) {
        // TODO: Fix me
        return 0;
    }
}
