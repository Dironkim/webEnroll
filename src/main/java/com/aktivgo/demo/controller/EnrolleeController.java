package com.aktivgo.demo.controller;

import com.aktivgo.demo.dao.EnrolleeListDao;
import com.aktivgo.demo.dao.ExamListDao;
import com.aktivgo.demo.model.Enrollee;
import com.aktivgo.demo.vars.Vars;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EnrolleeController {
    private final EnrolleeListDao enrollees = new EnrolleeListDao();
    private final ExamListDao exams = new ExamListDao();

   /* EnrolleeService enrolleeService;
    ExamService examService;

    {
        try {
            enrolleeService = new EnrolleeService(new EnrolleeDBDao());

            examService = new ExamService(new ExamDBDao());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @GetMapping("/enrollees")
    public String enrollees(@NotNull Model model) {
        model.addAttribute("title", "Список абитуриентов");
        model.addAttribute("enrollees", enrollees.getAll());
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, @NotNull Model model) {
        Enrollee enrollee = enrollees.get(id - 1).get();
        model.addAttribute("title", enrollee.getFullName());
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("exams", exams.getExamsByEnrolleeId(id - 1));
        model.addAttribute("passingScore", Vars.PASSING_SCORE);
        return "enrollee";
    }
}
