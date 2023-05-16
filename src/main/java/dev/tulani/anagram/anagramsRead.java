package dev.tulani.anagram;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class anagramsRead {

    @GetMapping("/")
        public String findAnagrams() throws IOException {
            long startTime = System.currentTimeMillis();
            int count = 0;
            Set<String> anagrams = new HashSet<>();
            ClassPathResource resource = new ClassPathResource("/files/hello.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String word ;

            while ((word= br.readLine()) != null) {
                for (String existing : anagrams) {
                    if (isAnagram(existing, word)) {
                        anagrams.add(existing + " and " + word);
                        count++;
                    }
                }
                anagrams.add(word);
            }
            br.close();

            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;

            return "Found " + count + " pairs of anagrams in " + timeTaken + " milliseconds:\n" + String.join("\n", anagrams);
        }
    private static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }else{
            int[] freq = new int[26];
            for (char c : s1.toCharArray()) {
                freq[c - 'a']++;
            }
            for (char c : s2.toCharArray()) {
                freq[c - 'a']--;
                if (freq[c - 'a'] < 0) {
                    return false;
                }
            }

        }
        return true;
    }
}