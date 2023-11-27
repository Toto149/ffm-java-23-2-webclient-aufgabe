package com.example.ffmjava232webclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//https://rickandmortyapi.com/api/character
@RequestMapping("/status")
@RestController
public class StatusController {
    @GetMapping
    public String get(){
        return "Up & running";
    }


}
