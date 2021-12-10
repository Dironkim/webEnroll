package com.aktivgo.demo.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@NotNull @RequestParam(name = "name", required = false, defaultValue = "World") String name, @NotNull Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
