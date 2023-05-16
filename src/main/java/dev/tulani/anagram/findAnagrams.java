//package dev.tulani.anagram;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@CrossOrigin("http://localhost:3000/")
//public class findAnagrams {
//    private static final int MIN_ANAGRAMS = 2;
//    @GetMapping("/anagrams/{length}")
//    public Object[] findAnagrams(@PathVariable int length) throws IOException {
//        Object[] myArray = new Object[3];
////        code checks that the length of the word is between 2 and 15 characters
//        if (length <= 1) {
//            throw new IllegalArgumentException("Word length must be greater than 1.");
//        } else if (length > 15) {
//            throw new IllegalArgumentException("Word length must be less than 16.");
//
//        }
////        Read from the files resource folder to ge and creates a classPathResource object from the file,maps through object to store anagram
//        String filePath = "/files/Dictionary.txt";
//        ClassPathResource resource = new ClassPathResource(filePath);
//        Map<String, Integer> countMap = new HashMap<>();
////        start time when executing algorithm
//        long startTime = System.currentTimeMillis();
////        try block reads line by line and trims . if line equals the lentgh wanted then the characters are sorted, stored as key in map object.
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                line = line.trim();
//                if (line.length() == length) {
//                    char[] chars = line.toCharArray();
//                    Arrays.sort(chars);
//                    String sortedLine = new String(chars);
//                    countMap.put(sortedLine, countMap.getOrDefault(sortedLine, 0) + 1);
//                }
//            }
//        }
////        Time it took for the algorithm to finish, optimize on simple and fewer steps
//        long endTime = System.currentTimeMillis();
//        long elapsedTime = endTime - startTime;
//        Map<String, Integer> result = new HashMap<>();
//        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
//            if (entry.getValue() >= MIN_ANAGRAMS) {
//                result.put(entry.getKey(), entry.getValue());
//            }
//        }
//        myArray[0]= elapsedTime;
//        myArray[1]= result;
//        myArray[2]= result.size();
//
//        return myArray;
//    }
//}
