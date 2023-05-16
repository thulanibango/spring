package dev.tulani.anagram;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class resources {
    private DaoService service;

    public resources(DaoService service){
        this.service =service;
    }
    @GetMapping("/anagram")
    public List<anagram> retrieveAll(){
        return service.findAll();

    }
    @GetMapping("/anagram/{id}")
    public anagram retrieveAnagram(@PathVariable int id) {
        anagram myanagram = service.findOne(id);
        if (myanagram == null) {
            throw new AnagramNotFoundException("id " + id);
        }
        return myanagram;
    }
    @DeleteMapping("/anagram/{id}")
    public void deleteAnagram(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/anagram")
    public ResponseEntity<Object> createAnagram(@RequestBody anagram myAnagram){
        anagram savedAnagram = service.saveAnagram(myAnagram);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAnagram.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
