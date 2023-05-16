package dev.tulani.anagram;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000/")
@EnableCaching
public class anagramFileManagement {
    private final String fileName = "/files/Dictionary.txt";
    private static final int MIN_ANAGRAMS = 2;
    @GetMapping("anagrams/all")
    @Cacheable("anagramCache")
    public  Map<String, String> readLines() throws IOException {
        Map<String, String> KeyValuePairs = new HashMap<>();
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line;
        int id =0;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            for(String token : tokens){
                String key = "key" + id;
                KeyValuePairs.put(key, token.trim());
                id++;
            }
        }
        reader.close();
        return KeyValuePairs;
    }
    @PostMapping("/anagrams/add")
    public String appendToFile(@RequestBody String content) throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        appendToFile(file, content);
        return content + "Added to file: ";
    }

    @GetMapping("/anagrams/search/{word}")
    public ResponseEntity<List<String>> searchFile(@PathVariable String word) {
        // search for the word in the text file
        List<String> lines = searchInFile(word);

        // return the matching lines as a response
        return ResponseEntity.ok(lines);
    }


    @GetMapping("/anagrams/{length}")
    public Object[] findAnagrams(@PathVariable int length) throws IOException {
        Object[] myArray = new Object[3];
//        code checks that the length of the word is between 2 and 15 characters
        if (length <= 1) {
            throw new IllegalArgumentException("Word length must be greater than 1.");
        } else if (length > 15) {
            throw new IllegalArgumentException("Word length must be less than 16.");

        }
//        Read from the files resource folder to ge and creates a classPathResource object from the file,maps through object to store anagram
//        String filePath;
//        filePath = fileName;
        ClassPathResource resource = new ClassPathResource(fileName);
        Map<String, Integer> countMap = new HashMap<>();
//        start time when executing algorithm
        long startTime = System.currentTimeMillis();
//        try block reads line by line and trims . if line equals the lentgh wanted then the characters are sorted, stored as key in map object.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == length) {
                    char[] chars = line.toCharArray();
                    Arrays.sort(chars);
                    String sortedLine = new String(chars);
                    countMap.put(sortedLine, countMap.getOrDefault(sortedLine, 0) + 1);
                }
            }
        }
//        Time it took for the algorithm to finish, optimize on simple and fewer steps
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= MIN_ANAGRAMS) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        myArray[0]= elapsedTime;
        myArray[1]= result;
        myArray[2]= result.size();

        return myArray;
    }

    private void appendToFile(File file, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(content);
        writer.newLine();
        writer.flush(); // ensure it's written to the file
        writer.close();
    }

    private List<String> searchInFile(String word) {
        List<String> matchingLines = new ArrayList<>();

        try {
            // get the file path to the text file in the resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("/files/Dictionary.txt")).getFile());
//            File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));


            // use Scanner to read the file line by line
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(word)) {
                    matchingLines.add(line);
                }
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingLines;
    }
}
