package server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Supplies random words for drawing.
 * Can load from a file or categorized word bank.
 * Provides word options to the drawer each round.
 */

// static class or singleton
// Generates or fetches words for each round.

import java.io.*;
import java.util.*;

public class WordGenerator {
    private List<String> words;
    private Random random;

    public WordGenerator(String filepath) throws IOException {
        words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        }
        random = new Random();
    }

    public List<String> getRandomWords(int count) {
        List<String> selection = new ArrayList<>();
        Collections.shuffle(words);
        for (int i = 0; i < count && i < words.size(); i++) {
            selection.add(words.get(i));
        }
        return selection;
    }
}