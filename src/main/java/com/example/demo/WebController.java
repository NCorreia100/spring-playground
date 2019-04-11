package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @RequestMapping("/")
    public  String getDefault(@RequestParam("q") String query){
        return "The parameter passed in was "+query;
    }
}
