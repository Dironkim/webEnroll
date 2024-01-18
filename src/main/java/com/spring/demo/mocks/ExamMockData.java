package com.spring.demo.mocks;

import com.spring.demo.models.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamMockData {

    public static List<Exam> createMockExams() {
        List<Exam> exams = new ArrayList<>();

        exams.add(new Exam("Math", 90, 1L));
        exams.add(new Exam("English", 85, 1L));
        exams.add(new Exam("Physics", 92, 2L));


        return exams;
    }
}
