package com.aktivgo.demo.controller;

import com.aktivgo.demo.model.Enrollee;
import com.aktivgo.demo.model.Exam;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EnrolleeController {
    @GetMapping("/enrollees")
    public String enrollees(@NotNull Model model){
        model.addAttribute("title","Список абитуриентов");
        List<Enrollee> enrollees = new ArrayList<>();
        enrollees.add(new Enrollee(1, "Vladislav", LocalDate.parse("2001-06-01")));
        enrollees.add(new Enrollee(2, "Pavlodar", LocalDate.parse("2000-11-06")));
        enrollees.add(new Enrollee(3, "Roman", LocalDate.parse("2001-12-30")));
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, @NotNull Model model){
        Enrollee enrollee =
                model.addAttribute("enrollee", enrollee);
        ArrayList<Exam> exams = // получение экзаменов, сданных абитуриентом
                model.addAttribute("exams", exams);
        return "enrollee";
    }

}
