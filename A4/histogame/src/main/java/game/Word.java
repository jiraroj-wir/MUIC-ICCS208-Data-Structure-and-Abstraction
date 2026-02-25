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

    // Returns the String representation of the word
    public String getWord() { return this.word; }

    // Returns a Histogram describing the character distribution of the word.
    public Histogram<Character> getHistogram() { return this.hist; }

    // Returns true if the Word represented by other can be formed using some
    // or all of the characters of this word
    @Override
    public boolean canForm(Word other) {
        // TODO: Fix me
        // return false;
        Histogram<Character> other_hist = other.getHistogram();

        for (Character ch : other_hist) {
            if (this.hist.getCount(ch) < other_hist.getCount(ch)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int compareTo(Word o) {
        // TODO: Fix me
        return 0;
    }
}
