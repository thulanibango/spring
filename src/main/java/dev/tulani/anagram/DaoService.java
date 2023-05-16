package dev.tulani.anagram;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class DaoService {
//    private final String fileName = "/files/Dictionary.txt";
    private static List<anagram> anagrams =new ArrayList<>();
    private static int anagramCount = 3;
    static{
        anagrams.add(new anagram(1,"keep","peek"));
        anagrams.add(new anagram(2,"Meat","Team"));
        anagrams.add(new anagram(3,"lil","ill"));

    }
    public List<anagram> findAll(){
        return anagrams;
    }
    public anagram findOne(int id) {
        Predicate<? super anagram> predicate = user -> user.getId().equals(id);
        return anagrams.stream().filter(predicate).findFirst().orElse(null);
    }
    public anagram saveAnagram(anagram anagram){
        anagram.setId(++anagramCount);
        anagrams.add(anagram);
        return anagram;
    }
    public void deleteById(int id) {
        Predicate<? super anagram> predicate = anagram -> anagram.getId().equals(id);
        anagrams.removeIf(predicate);
    }


}
