package com.empiricus.statusviajante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;

@RequestMapping("/usuario")
@RestController
public class FirstController {

    @GetMapping({"/", "/main"})
    public String hello(){
        return "olá, mundo";
    }
}
