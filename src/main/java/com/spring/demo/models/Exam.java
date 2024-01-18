package com.spring.demo.models;

import com.spring.demo.entities.ExamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class Exam {

    private String subject;

    private Integer score;

    private Long idEnrollee;
    public Exam(ExamEntity exam) {
        setSubject(exam.getSubject());
        setScore(exam.getScore());
        setIdEnrollee(exam.getIdEnrollee());
    }
    public boolean notNull() {
        return subject != null && score != null;
    }
}