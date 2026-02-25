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

    // Return -1 if this word is shorter than the word represented by o OR // when this word and the word represented by
    // o have the same length but // this word comes before the word represented by o alphabetically. Return zero if
    // this word and o word are identical. Return +1 otherwise.
    @Override
    public int compareTo(Word o) {
        if (this.word.equals(o.word)) { // identical
            return 0;
        }

        int diff = this.word.length() - o.word.length();
        if (diff < 0) { // compare length
            return -1;
        } else if (diff > 0) {
            return 1;
        }

        int alpha = this.word.compareTo(o.word); // same length, check alphabetical order
        if (alpha < 0) {
            return -1;
        } else if (alpha > 0) {
            return 1;
        }

        return 0; // then it supposed to be identical
    }
}
