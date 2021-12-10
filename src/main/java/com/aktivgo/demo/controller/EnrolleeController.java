package com.aktivgo.demo.controller;

import com.aktivgo.demo.model.Enrollee;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EnrolleeController {
    @GetMapping("/enrollees")
    public String enrollees(@NotNull Model model){
        model.addAttribute("title","Список абитуриентов");
        List<Enrollee> enrollees = new ArrayList<>();
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

}
