package com.aktivgo.demo.controller;

import com.aktivgo.demo.dao.EnrolleeListDao;
import com.aktivgo.demo.model.Enrollee;
import com.aktivgo.demo.model.Exam;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class EnrolleeController {
    private EnrolleeListDao enrollees = new EnrolleeListDao();

    @GetMapping("/enrollees")
    public String enrollees(@NotNull Model model) {
        model.addAttribute("title", "Список абитуриентов");
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, @NotNull Model model) {
        Enrollee enrollee = enrollees.get(id - 1).get();
        model.addAttribute("title", enrollee.getFullName());
        model.addAttribute("enrollee", enrollee);
        ArrayList<Exam> exams = new ArrayList<>();
        model.addAttribute("exams", exams);
        return "enrollee";
    }
}
