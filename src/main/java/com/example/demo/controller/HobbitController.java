package com.example.demo.controller;

import com.example.demo.model.Hobbit;
import com.example.demo.service.HobbitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HobbitController {
    private HobbitService hobbitService;

    public HobbitController(HobbitService hobbitService) {
        this.hobbitService = hobbitService;
    }

    @GetMapping("/hobbits")
    List<Hobbit> findAll() {
        return hobbitService.findAll();
    }
}
