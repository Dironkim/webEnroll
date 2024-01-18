package com.spring.demo.dao;

import com.spring.demo.mocks.EnrolleeMockData;
import com.spring.demo.mocks.ExamMockData;
import com.spring.demo.models.Enrollee;
import com.spring.demo.models.Exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamListDao implements Dao<Exam>{
    private final List<Exam> exams = ExamMockData.createMockExams();
    @Override
    public long size() {
        return exams.size();
    }
    public List<Exam> getExamsByEnrolleeId(Long idEnrollee) {
        List<Exam> examList = new ArrayList<>();

        for (Exam exam : exams) {
            if (exam.getIdEnrollee().equals(idEnrollee)) {
                examList.add(exam);
            }
        }

        return examList;
    }
    @Override//pointless
    public Optional<Exam> get(Long id) {
        return Optional.of(exams.get(id.byteValue()));
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
