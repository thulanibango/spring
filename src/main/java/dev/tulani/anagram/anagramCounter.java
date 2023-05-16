package dev.tulani.anagram;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class anagramCounter {

    private static final int MIN_ANAGRAMS = 2;

    @GetMapping("/anagrams/count/{number}")
    public int countAnagrams(@PathVariable int number) throws IOException {
        String word = String.valueOf(number);
        if (word.length() <= 1) {
            throw new IllegalArgumentException("Number must have at least 2 digits.");
        }

        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String sortedWord = new String(chars);

        String filePath = "/files/Dictionary.txt";
        ClassPathResource resource = new ClassPathResource(filePath);
        Map<String, Integer> countMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (line.length() == word.length()) {
                    char[] lineChars = line.toCharArray();
                    Arrays.sort(lineChars);
                    String sortedLine = new String(lineChars);
                    countMap.put(sortedLine, countMap.getOrDefault(sortedLine, 0) + 1);
                }
            }
        }

        int count = countMap.getOrDefault(sortedWord, 0);
        System.out.println("Number of anagrams for " + number + ": " + count);

        return count;
    }
}
