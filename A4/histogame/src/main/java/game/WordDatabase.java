package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// HINT(s):
//   To read from src/resources/<filename>
//   InputStream is = getClass().getClassLoader().getResourceAsStream(filename);

public class WordDatabase implements IDatabase {
    private final List<Word> words;

    private WordDatabase() { this.words = new ArrayList<>(); }

    // Load all the words from a file given by filename
    public WordDatabase(String filename) throws IOException {
        this();

        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            throw new FileNotFoundException("file: " + filename + "not found!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    this.words.add(new Word(line));
                }
            }
        }
    }

    // Adds a word to the database.
    @Override
    public void add(Word w) {
        // TODO:
        this.words.add(new Word(w));
    }

    // Removes w from the database and has no effect if w is not present
    @Override
    public void remove(Word w) {
        // TODO:
        if (words.contains(w)) {
            words.remove(words.indexOf(w));
        }
    }

    // Returns a List of Words in the database whose length is exactly l.
    @Override
    public List<Word> getWordWithLength(int l) {
        // TODO:
        // return null;
        List<Word> lst = new ArrayList<>();

        for (word w : this.words) {
            if (w.getWord().length() == l) {
                lst.add(w);
            }
        }

        return lst;
    }

    @Override
    public List<Word> getAllSubWords(Word w, int minLen) {
        // TODO:
        return null;
    }

    @Override
    public boolean contains(Word o) {
        // TODO:
        return false;
    }
}
