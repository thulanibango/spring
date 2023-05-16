//package dev.tulani.anagram;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class permutations {
//    @GetMapping("/permutations")
//    public Object[] findAnagrams() throws IOException {
//        // Read lines from the file and split them into words
//        Map<String, String> words = anagramFileManagement.readLines("/files/Dictionary.txt");
//        // Create a map to store the counts of each anagram
//        Map<String, Integer> anagramCounts = new HashMap<>();
//        long startTime = System.nanoTime();
//        Object[] myArray = new Object[2];
//        // Iterate over each word in the map and sort its letters
//        for (String key : words.keySet()) {
//            String word = words.get(key);
//            char[] chars = word.toCharArray();
//            Arrays.sort(chars);
//            String sortedWord = new String(chars);
//            // Increment the count of the anagram with the same sorted letters
//            if (!anagramCounts.containsKey(sortedWord)) {
//                anagramCounts.put(sortedWord, 1);
//            } else {
//                anagramCounts.put(sortedWord, anagramCounts.get(sortedWord) + 1);
//            }
//        }
//        long endTime = System.nanoTime();
//        long elapsedTime = endTime - startTime;
//        // Filter the anagram counts to only include counts greater than 1
//        anagramCounts.entrySet().removeIf(entry -> entry.getValue() < 2);
//        // Print the anagram counts and the time it took to find them
//        System.out.println("Anagrams:");
//        for (String key : anagramCounts.keySet()) {
//            int count = anagramCounts.get(key);
//            System.out.println(key + ": " + count);
//        }
//        System.out.println("Time: " + elapsedTime + " nanoseconds");
//        myArray[0]= elapsedTime;
//        myArray[1]= anagramCounts;
//        return myArray;
//    }
//
//}
