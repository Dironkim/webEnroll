package com.aktivgo.demo.dao;

import com.aktivgo.demo.model.Exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamListDao implements Dao<Exam> {
    private List<Exam> exams = new ArrayList<>();

    public ExamListDao() {
        exams.add(new Exam("math", 68, 1));
        exams.add(new Exam("math", 100, 2));
        exams.add(new Exam("math", 80, 3));
        exams.add(new Exam("informatics", 80, 1));
        exams.add(new Exam("informatics", 85, 2));
        exams.add(new Exam("informatics", 90, 3));
    }

    public List<Exam> getExamsByEnrolleeId(int idEnrollee) {
        List<Exam> examList = new ArrayList<>();

        for (Exam exam : exams) {
            if (exam.getIdEnrollee() == idEnrollee) {
                examList.add(exam);
            }
        }

        return examList;
    }

    @Override
    public int size() {
        return exams.size();
    }

    @Override
    public Optional<Exam> get(int id) {
        return Optional.of(exams.get(id));
    }

    @Override
    public List<Exam> getAll() {
        return exams;
    }

    @Override
    public void save(Exam exam) {
        exams.add(exam);
    }

    @Override
    public void delete(Exam exam) {
        exams.remove(exam);
    }
}
