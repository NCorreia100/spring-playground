package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class WebController {

    @GetMapping("/")
    public String getDefault(){
        return "Jeff and Kay are the best instructors!";
    }
}
