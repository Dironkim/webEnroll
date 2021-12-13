package com.aktivgo.demo.controller;

import com.aktivgo.demo.dao.EnrolleeDBDao;
import com.aktivgo.demo.dao.ExamDBDao;
import com.aktivgo.demo.model.Enrollee;
import com.aktivgo.demo.model.Exam;
import com.aktivgo.demo.service.EnrolleeService;
import com.aktivgo.demo.service.ExamService;
import com.aktivgo.demo.vars.Vars;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class EnrolleeController {
   /* private final EnrolleeListDao enrollees = new EnrolleeListDao();
    private final ExamListDao exams = new ExamListDao();*/

    private Long idEnrollee = 0L;

    EnrolleeService enrollees;
    ExamService exams;

    {
        try {
            enrollees = new EnrolleeService(new EnrolleeDBDao());

            exams = new ExamService(new ExamDBDao());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/enrollees")
    public String enrollees(@NotNull Model model) {
        model.addAttribute("title", "Список абитуриентов");
        model.addAttribute("enrollees", enrollees.getAllEnrollees());
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable Long id, @NotNull Model model) {
        Enrollee enrollee = enrollees.getEnrollee(id);
        model.addAttribute("title", enrollee.getFullName());
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("exams", exams.getExamsByEnrolleeId(id));
        model.addAttribute("passingScore", Vars.PASSING_SCORE);
        return "enrollee";
    }

    @GetMapping("/add")
    public String enrolleeForm(@NotNull Model model) {
        model.addAttribute("title", "Добавление абитуриента");
        Enrollee enrollee = new Enrollee();
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrollees.sizeEnrollees() + 1);
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute @NotNull Enrollee enrollee, @NotNull Model model) {
        enrollee.setId(enrollees.sizeEnrollees());
        enrollees.save(enrollee);
        model.addAttribute("enrollees", enrollees);
        return "redirect:/enrollees";
    }

    @GetMapping("/exam/{id}")
    public String examForm(@PathVariable Long id, @NotNull Model model) {
        model.addAttribute("title", "Добавление экзамена");
        ArrayList<String> subjects = Vars.subjects;
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", subjects.get(0));
        idEnrollee = id;
        model.addAttribute("exam",new Exam());
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(@ModelAttribute @NotNull Exam exam, @NotNull Model model) {
        exam.setIdEnrollee(idEnrollee);
        exams.save(exam);
        model.addAttribute("exams", exams);
        return "redirect:/enrollee/" + exam.getIdEnrollee();
    }
}
