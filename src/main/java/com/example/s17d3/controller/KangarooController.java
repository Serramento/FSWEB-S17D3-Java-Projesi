package com.example.s17d3.controller;

import com.example.s17d3.entity.Kangaroo;
import com.example.s17d3.validation.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        System.out.println("Post construct ne zaman çalıştı");
        kangaroos= new HashMap<>();
        kangaroos.put(1, new Kangaroo(1, "dummyName", 80d, 200d, "female", Boolean.FALSE));
        kangaroos.put(2, new Kangaroo(2, "maleDummyName", 80d, 200d, "male", Boolean.FALSE));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Kangaroo> getAll(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo get(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        Kangaroo kangaroo= kangaroos.get(id);
        ZooValidation.isKangarooExist(kangaroo, id);
        return kangaroos.get(id);
    }

    @PostMapping
    public ResponseEntity<Kangaroo> save(@RequestBody Kangaroo kangaroo){
        ZooValidation.isIdValid(kangaroo.getId());
        ZooValidation.isNameValid(kangaroo.getName());
        kangaroos.put(kangaroo.getId(), kangaroo);
        return new ResponseEntity<>(kangaroo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id, @RequestBody Kangaroo newKangaroo){
        ZooValidation.isIdValid(id);
        ZooValidation.isNameValid(newKangaroo.getName());
        newKangaroo.setId(id);
        kangaroos.put(id, newKangaroo);
        return newKangaroo;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        kangaroos.remove(id);
    }
}
