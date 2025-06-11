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

public class WordGenerator {
    
    private static final String path = "assets/words.txt";

    private WordGenerator() {}

    private static ArrayList<String> parseWords () {

        ArrayList<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        return words;
    }

    public static List<String> getRandomWords(int count) {
        List<String> copy = new ArrayList<>(parseWords());
        Collections.shuffle(copy);
        return copy.subList(0, count);
    }

}
