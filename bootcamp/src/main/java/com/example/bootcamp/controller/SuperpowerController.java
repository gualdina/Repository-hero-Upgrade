package com.example.bootcamp.controller;

import com.example.bootcamp.model.Superpower;
import com.example.bootcamp.service.SuperpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class SuperpowerController {

    @Autowired
    SuperpowerService superpowerService;

    @GetMapping("/getSuperpowers")
    public List<Superpower> getSuperpower() {
        return superpowerService.findAll();
    }
}
