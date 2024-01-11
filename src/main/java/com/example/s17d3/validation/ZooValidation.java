package com.example.s17d3.validation;

import com.example.s17d3.entity.Kangaroo;
import com.example.s17d3.entity.Koala;
import com.example.s17d3.exceptions.ZooException;
import org.springframework.http.HttpStatus;

public class ZooValidation {
    public static void isIdValid(Integer id){
        if(id == null){
            throw new ZooException("id can not be null", HttpStatus.BAD_REQUEST);
        }
        if(id > 100){
            throw new ZooException("id can not bigger than 100", HttpStatus.BAD_REQUEST);
        }
    }
    public static void isNameValid(String name){
        if(name == null){
            throw new ZooException("name can not be null", HttpStatus.BAD_REQUEST);
        }
    }

    public static void isKangarooExist(Kangaroo kangaroo, Integer id) {
        if(kangaroo == null){
            throw new ZooException("kangaroo not found with id: "+ id, HttpStatus.NOT_FOUND);
        }
    }

    public static void isKoalaExist(Koala koala, Integer id) {
        if(koala == null){
            throw new ZooException("koala not found with id: "+ id, HttpStatus.NOT_FOUND);
        }
    }
}
