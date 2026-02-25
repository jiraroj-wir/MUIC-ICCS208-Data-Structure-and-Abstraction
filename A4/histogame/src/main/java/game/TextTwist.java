package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class TextTwist {
    // derive from the example, it should be sth close to this
    private static final int BASE_WORD_LENGTH = 6;
    private static final int MIN_WORD_LENGTH = 3;
    private static final int WORDS_PER_LINE = 5;

    private static boolean gameLoop(WordDatabase dictionary, Scanner scanner, Random random) {
        Word seed = pickSeed(dictionary, random);
        List<Word> answers = dictionary.getAllSubWords(seed, MIN_WORD_LENGTH);

        answers.sort(null);

        Set<String> words = new HashSet<>();
        for (Word word : answers) {
            words.add(word.getWord());
        }

        Set<String> guessed = new HashSet<>();

        String letters = shuffleLetters(seed.getWord(), random);
        long startTime = System.currentTimeMillis();

        while (true) {
            printStat(startTime, guessed.size(), answers.size(), letters);

            if (!scanner.hasNextLine()) {
                return false;
            }

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.isEmpty()) {
                continue;
            }

            if ("q".equals(input)) {
                return false;
            }
            if ("!".equals(input)) {
                printWordGrid(answers, guessed, true);
                System.out.printf("\n");
                return true;
            }
            if ("?".equals(input)) {
                printWordGrid(answers, guessed, false);
                System.out.printf("\n");
                continue;
            }

            // NOTE: creative feature, shuffle the letters, I found this feature useful in this kind of game, sometimes
            // you see new patterns with different permutations
            if (SHUFFLE_SHORTCUT.equals(input)) {
                letters = shuffleLetters(seed.getWord(), random);
                continue;
            }

            if (input.length() < MIN_WORD_LENGTH) {
                continue;
            }

            if (words.contains(input)) {
                guessed.add(input);
            }

            if (guessed.size() == answers.size()) { // player won
                printWordGrid(answers, guessed, true);
                System.out.printf("\n");

                return true;
            }
        }
    }

    private static Word pickSeed(WordDatabase dictionary, Random random) {
        List<Word> seeds = dictionary.getWordWithLength(BASE_WORD_LENGTH);

        if (seeds.isEmpty()) {
            throw new IllegalStateException("The dictionary has no 6-letter words");
        }

        return seeds.get(random.nextInt(seeds.size())); // idk how this works
    }

    // for shuffle command; creativity
    private static final String SHUFFLE_SHORTCUT = "s";

    private static String shuffleLetters(String word, Random random) {
        List<Character> chars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            chars.add(c);
        }

        Collections.shuffle(chars, random); // use build-ins to shuffle

        StringBuilder builder = new StringBuilder(word.length());
        for (char c : chars) {
            builder.append(c);
        }

        return builder.toString();
    }

    private static void printStat(long startTime, int found, int total, String letters) {
        double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;
        System.out.printf("Elapsed Time: %.2f seconds, Score: %d/%d%n", elapsed, found, total);
        System.out.printf("Commands: q - quit, ! - give up, ? - info, s - shuffle letters"
                          + "\n");
        System.out.printf("[" + letters + "] > "
                          +
                          "\n"); // sorry for this cursed formatting, something is wrong with my lvim's code formatter
    }

    private static void printWordGrid(List<Word> words, Set<String> guessed, boolean revealAll) {
        int count = 0;

        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            String display;

            if (revealAll || guessed.contains(word.getWord())) {
                display = word.getWord();
            } else {
                display = mask(word.getWord().length());
            }

            if (count > 0) {
                if (count % WORDS_PER_LINE == 0) {
                    System.out.printf("\n");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.print(display);
            count++;
        }

        if (count > 0) {
            System.out.println();
        }
    }

    private static String mask(int length) {
        char[] chars = new char[length];

        for (int i = 0; i < length; i++) {
            chars[i] = '?';
        }

        return new String(chars);
    }

    public static void main(String[] args) throws IOException {
        WordDatabase dictionary = new WordDatabase("linuxwords.txt"); // filepath
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            if (!gameLoop(dictionary, scanner, random)) {
                break;
            }
        }
    }
}
