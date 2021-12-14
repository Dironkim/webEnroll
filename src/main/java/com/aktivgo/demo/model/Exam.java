package com.aktivgo.demo.model;

import com.aktivgo.demo.entity.ExamEntity;
import org.jetbrains.annotations.NotNull;

public class Exam {
    private Long id;

    @NotNull
    private String subject;

    @NotNull
    private Integer score;

    @NotNull
    private Long idEnrollee;

    public Exam() {
        id = 0L;
        subject = "";
        score = 0;
        idEnrollee = 0L;
    }

    public Exam(Long id, @NotNull String subject, int score, @NotNull Long idEnrollee) {
        this.id = id;
        this.subject = subject;
        this.score = score;
        this.idEnrollee = idEnrollee;
    }

    public Exam(@NotNull ExamEntity exam) {
        this.subject = exam.getSubject();
        this.score = exam.getScore();
        this.idEnrollee = exam.getIdEnrollee();
    }

    public @NotNull String getSubject() {
        return subject;
    }

    public void setSubject(@NotNull String subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getIdEnrollee() {
        return idEnrollee;
    }

    public void setIdEnrollee(long idEnrollee) {
        this.idEnrollee = idEnrollee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}