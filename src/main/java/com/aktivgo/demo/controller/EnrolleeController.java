package com.aktivgo.demo.controller;

import com.aktivgo.demo.dao.EnrolleeListDao;
import com.aktivgo.demo.dao.ExamListDao;
import com.aktivgo.demo.model.Enrollee;
import com.aktivgo.demo.model.Exam;
import com.aktivgo.demo.vars.Vars;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

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
        model.addAttribute("exams", exams.getExamsByEnrolleeId(id));
        model.addAttribute("passingScore", Vars.PASSING_SCORE);
        return "enrollee";
    }

    @GetMapping("/add")
    public String enrolleeForm(@NotNull Model model) {
        model.addAttribute("title", "Добавление абитуриента");
        Enrollee enrollee = new Enrollee(enrollees.size() + 1);
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrollee.getId());
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute @NotNull Enrollee enrollee, @NotNull Model model) {
        enrollees.save(enrollee);
        model.addAttribute("enrollees", enrollees);
        return "redirect:/enrollees";
    }

    @GetMapping("/exam/{id}")
    public String examForm(@PathVariable Long id, @NotNull Model model) {
        model.addAttribute("title", "Добавление экзамена");
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Математика");
        subjects.add("Информатика");
        subjects.add("Русский язык");
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", subjects.get(0));
        Exam exam = new Exam();
        exam.setIdEnrollee(id);
        model.addAttribute("exam", exam);
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(@ModelAttribute Exam exam, @NotNull Model model) {
        exams.save(exam);
        model.addAttribute("exams", exams);
        return "redirect:/enrollee/" + exam.getIdEnrollee();
    }
}
