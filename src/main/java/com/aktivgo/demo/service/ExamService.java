package com.aktivgo.demo.service;

import com.aktivgo.demo.dao.Dao;
import com.aktivgo.demo.entity.ExamEntity;
import com.aktivgo.demo.model.Exam;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {
    private Dao<ExamEntity> examDao;

    public ExamService(Dao<ExamEntity> examDao) {
        this.examDao = examDao;
    }

    public long sizeExams() {
        return examDao.size();
    }

    public @NotNull List<Exam> getAllExams() {
        return getExams(examDao.getAll());
    }

    public ExamEntity getExam(@NotNull Long id) {
        return examDao.get(id).get();
    }

    public void save(@NotNull Exam exam) {
        ExamEntity examEntity = new ExamEntity(exam);
        examDao.save(examEntity);
    }

    public void delete(@NotNull Exam exam) {
        ExamEntity examEntity = new ExamEntity(exam);
        examDao.delete(examEntity);
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
