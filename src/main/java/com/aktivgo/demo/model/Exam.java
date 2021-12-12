package com.aktivgo.demo.model;

import com.aktivgo.demo.entity.ExamEntity;
import org.jetbrains.annotations.NotNull;

public class Exam {
    private String subject;
    private int score;
    private long idEnrollee;

    public Exam(@NotNull String subject, int score, long idEnrollee) {
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
}