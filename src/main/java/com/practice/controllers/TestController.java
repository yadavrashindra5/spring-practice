package com.practice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseEntity<Map<String,String>> getName(){
        Map<String,String>map=new HashMap<>();
        map.put("name","Rashindra");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
