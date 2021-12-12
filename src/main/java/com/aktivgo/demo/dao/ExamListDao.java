package com.aktivgo.demo.dao;

import com.aktivgo.demo.model.Exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamListDao implements Dao<Exam> {
    private final List<Exam> exams = new ArrayList<>();

    public ExamListDao() {
        exams.add(new Exam("Математика", 68, 1L));
        exams.add(new Exam("Математика", 100, 2L));
        exams.add(new Exam("Математика", 80, 3L));
        exams.add(new Exam("Информатика", 80, 1L));
        exams.add(new Exam("Информатика", 85, 2L));
        exams.add(new Exam("Информатика", 90, 3L));
        exams.add(new Exam("Русский язык", 20, 1L));
        exams.add(new Exam("Русский язык", 100, 2L));
        exams.add(new Exam("Русский язык", 100, 3L));
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
    public long size() {
        return exams.size();
    }

    @Override
    public Optional<Exam> get(long id) {
        return Optional.of(exams.get((int) id));
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
