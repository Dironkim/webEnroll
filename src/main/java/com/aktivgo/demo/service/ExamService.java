package com.aktivgo.demo.service;

import com.aktivgo.demo.dao.Dao;
import com.aktivgo.demo.entity.ExamEntity;
import com.aktivgo.demo.model.Exam;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExamService {
    private Dao examDao;

    public ExamService(Dao examDao) {
        this.examDao = examDao;
    }

    public long sizeExams() {
        return examDao.size();
    }

    public @NotNull List<Exam> getAllExams() {
        return getExams(examDao.getAll());
    }

    public Exam getExam(@NotNull Long id) {
        return (Exam) examDao.get(id).get();
    }

    public void save(@NotNull Exam exam) {
        ExamEntity examEntity = new ExamEntity(exam);
        examDao.save(examEntity);
    }

    public List<Exam> getExams(@NotNull List<ExamEntity> examEntities) {
        List<Exam> exams = new ArrayList<>();
        for (ExamEntity examEntity : examEntities) {
            exams.add(new Exam(examEntity));
        }
        return exams;
    }

    public List<Exam> getExamsByEnrolleeId(@NotNull Long idEnrollee) {
        List<Exam> examList = new ArrayList<>();

        for (Exam exam : getAllExams()) {
            if (exam.getIdEnrollee() == idEnrollee) {
                examList.add(exam);
            }
        }

        return examList;
    }
}
