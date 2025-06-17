package com.dea.ms_security.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api")
public class MainController {
    
    @GetMapping("/intro")
    public ResponseEntity<String> intro() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("HELLO i dont need authorization");
    }
    
    
}
