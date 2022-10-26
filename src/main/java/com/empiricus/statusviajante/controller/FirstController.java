package com.empiricus.statusviajante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.processing.Generated;

@Controller
public class FirstController {

    @GetMapping({"/", "/main"})
    public String main(@RequestParam(value =  "name", defaultValue = "world...Spring", required = true)
                           String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }
}
