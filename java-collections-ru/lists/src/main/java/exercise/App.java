package exercise;

import java.util.Arrays;
import java.util.ArrayList;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        //make case insensitive
        String lettersLowCase = letters.toLowerCase();
        String[] lettersSplit = lettersLowCase.split("");
        String wordLowCase = word.toLowerCase();
        String[] wordLettersSplit = wordLowCase.split("");
        //create lists
        ArrayList<String> listOfLetters = new ArrayList<>(Arrays.asList(lettersSplit));
        ArrayList<String> wordLetters = new ArrayList<>(Arrays.asList(wordLettersSplit));
        for (String letter: wordLetters) {
            if (listOfLetters.contains(letter)) {
                listOfLetters.remove(letter);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
