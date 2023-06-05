package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ByeController {
    @GetMapping("/bye")
    public String bye(Model model) {
        model.addAttribute("username" , "CJW");

        return "bye";
    }
}
