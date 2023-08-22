package exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        if (sentence.equals("")) {
            return Collections.emptyMap();
        }
        String[] words = sentence.split(" ");
        Arrays.sort(words);
        Map<String, Integer> wordsMap = new HashMap<>();
        String prev = null;
        int wordCount = 1;
        for (String word : words) {
            if (word.equals(prev)) {
                wordCount++;
            } else {
                wordCount = 1;
            }
            prev = word;
            wordsMap.put(word, wordCount);
        }
        return wordsMap;
    }

    public static String toString(Map<String, Integer> map) {
        String couples = "";
        String couple = "";
        if (map.isEmpty()) {
            return "{}";
        }
        for (Map.Entry<String, Integer> item: map.entrySet()) {
            couples = couple + "  " + item.getKey() + ": " + item.getValue() + "\n";
            couple = couples;
        }
        return "{\n" + couples + "}";
    }
}

