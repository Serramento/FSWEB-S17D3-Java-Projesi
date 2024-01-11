package com.example.s17d3.controller;

import com.example.s17d3.entity.Kangaroo;
import com.example.s17d3.entity.Koala;
import com.example.s17d3.validation.ZooValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        System.out.println("Post construct ne zaman çalıştı");
        koalas= new HashMap<>();
        koalas.put(1, new Koala(1, "dummyKoala", 90d, 100d, "female"));
        koalas.put(2, new Koala(2, "maleDummyName", 80d, 200d, "male"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Koala> getAll(){
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala get(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        Koala koala= koalas.get(id);
        ZooValidation.isKoalaExist(koala, id);
        return koalas.get(id);
    }

    @PostMapping
    public ResponseEntity<Koala> save(@RequestBody Koala koala){
        ZooValidation.isIdValid(koala.getId());
        ZooValidation.isNameValid(koala.getName());
        koalas.put(koala.getId(), koala);
        return new ResponseEntity<>(koala, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id, @RequestBody Koala newKoala){
        ZooValidation.isIdValid(id);
        ZooValidation.isNameValid(newKoala.getName());
        newKoala.setId(id);
        koalas.put(id, newKoala);
        return newKoala;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id){
        ZooValidation.isIdValid(id);
        koalas.remove(id);
    }
}
