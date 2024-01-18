package com.spring.demo.controllers;

import com.spring.demo.dao.EnrolleeDBDao;
import com.spring.demo.dao.EnrolleeListDao;
import com.spring.demo.dao.ExamDBDao;
import com.spring.demo.dao.ExamListDao;
import com.spring.demo.mocks.ExamMockData;
import com.spring.demo.models.Enrollee;
import com.spring.demo.models.Exam;
import com.spring.demo.service.EnrolleeService;
import com.spring.demo.service.ExamService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EnrolleeController {
    Long enrolleeId;
    Long curId;
    //EnrolleeListDao enrolleeDAO = new EnrolleeListDao();
    //ExamListDao examDao = new ExamListDao();
    EnrolleeService enrolleeService;

    {
        try {
            enrolleeService = new EnrolleeService(new EnrolleeDBDao());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    ExamService examService;

    {
        try {
            examService = new ExamService(new ExamDBDao());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/enrollees")
    public String enrollees(Model model) {
        model.addAttribute("title", "Список абитуриентов");
        List<Enrollee> enrollees = enrolleeService.getAllEnrollees();
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }

    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable int id, Model model) {
        Enrollee enrollee = enrolleeService.getAllEnrollees().get(id);
        model.addAttribute("enrollee", enrollee);
        List<Exam> exams = examService.getExamsByEnrolleeId(enrollee.getId());
        model.addAttribute("exams", exams);
        return "enrollee";
    }
    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title", "Добавление абитуриента");
        Enrollee enrollee = new Enrollee();
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrolleeService.getAllEnrollees().size());
        return "add";
    }

    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        if (enrollee.notNull()) {
            curId = (long) enrolleeService.getAllEnrollees().size();
            enrollee.setId(curId);
            enrolleeService.save(enrollee);
            List<Enrollee> enrollees = enrolleeService.getAllEnrollees();
            model.addAttribute("enrollees", enrollees);
        }
        return "redirect:/enrollees";
    }

    @GetMapping("/exam/{id}")
    public String examForm(@PathVariable long id, Model model) {
        enrolleeId = id;
        model.addAttribute("title", "Добавление экзамена'");
        List<String> subjects = new ArrayList<>();
        subjects.add("Русский язык");
        subjects.add("Математика");
        subjects.add("Физика");
        subjects.add("Информатика");
        model.addAttribute("subjects", subjects);
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("id", id);
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(@ModelAttribute Exam exam, Model model) {
        if (exam.notNull() && examService.getAllExams().stream()
                .filter(x -> Objects.equals(x.getSubject(), exam.getSubject()) && Objects.equals(x.getIdEnrollee(), enrolleeId))
                .toList().isEmpty()) {
            exam.setIdEnrollee(enrolleeId);
            examService.save(exam);
            model.addAttribute("exam", exam);
        }
        return "redirect:/enrollees";
    }

}
