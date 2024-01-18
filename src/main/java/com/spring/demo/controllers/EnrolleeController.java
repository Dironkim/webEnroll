package com.spring.demo.controllers;

import com.spring.demo.dao.EnrolleeListDao;
import com.spring.demo.dao.ExamListDao;
import com.spring.demo.mocks.ExamMockData;
import com.spring.demo.models.Enrollee;
import com.spring.demo.models.Exam;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EnrolleeController {
    Long enrolleeId;
    EnrolleeListDao enrolleeDAO = new EnrolleeListDao();
    ExamListDao examDao = new ExamListDao();
    @GetMapping("/enrollees")
    public String enrollees(Model model){
        model.addAttribute("title","Список абитуриентов");
        List<Enrollee> enrollees =enrolleeDAO.getAll(); // получение списка абитуриентов
        model.addAttribute("enrollees", enrollees);
        return "enrollees";
    }
    @GetMapping("/enrollee/{id}")
    public String enrollee(@PathVariable Long id, Model model){
        Optional<Enrollee> enrolleeOptional = enrolleeDAO.get(id);

        if (enrolleeOptional.isPresent()) {
            Enrollee enrollee = enrolleeOptional.get();
            model.addAttribute("enrollee", enrollee);

            // Получение экзаменов, сданных абитуриентом
            List<Exam> exams = examDao.getExamsByEnrolleeId(id);// Логика получения экзаменов
            model.addAttribute("exams", exams);

            return "enrollee";
        }
        return "home";
    }
    @GetMapping("/add")
    public String enrolleeForm(Model model) {
        model.addAttribute("title","Добавление абитуриента");
        Enrollee enrollee = new Enrollee ();
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("number", enrolleeDAO.size());
        return "add";
    }
    @PostMapping("/add")
    public String enrolleeSubmit(@ModelAttribute Enrollee enrollee, Model model) {
        enrollee.setId((long)enrolleeDAO.getAll().size());
        enrolleeDAO.save(enrollee);
        List<Enrollee> enrollees = enrolleeDAO.getAll();// получение списка абитуриентов
        model.addAttribute("enrollees", enrollees);
        return "redirect:/enrollees";
    }
    @GetMapping("/exam{id}")
    public String examForm(@PathVariable Long id, Model model) {
        model.addAttribute("title","Добавление экзамена'");
        List<String> subjects = ExamMockData.createMockExams().stream()
                .map(Exam::getSubject)
                .collect(Collectors.toList());// список всех дисциплин для формы
        model.addAttribute("subjects", subjects);
        model.addAttribute("subject", subjects.get(0));
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("id", id);
        enrolleeId =id;
        return "exam";
    }

    @PostMapping("/exam")
    public String examSubmit(@ModelAttribute Exam exam, Model model) {
        if (!exam.getSubject().isEmpty() && exam.getScore() >= 0 && exam.getScore() <= 100 && !containsSubject(exam.getSubject())) {
            exam.setIdEnrollee(enrolleeId);
            examDao.save(exam);
            model.addAttribute("exams", examDao);
        }
        return "redirect:/enrollee/"+enrolleeId;
    }
    private boolean containsSubject(String subject) {
        for(Exam exam : examDao.getExamsByEnrolleeId(enrolleeId)) {
            if (exam.getSubject().equals(subject)) {
                return true;
            }
        }
        return false;
    }
}
