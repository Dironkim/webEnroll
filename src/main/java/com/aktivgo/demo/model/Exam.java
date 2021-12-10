package com.aktivgo.demo.model;

import org.jetbrains.annotations.NotNull;

public class Exam {
    private String subject;
    private int score;
    private int idEnrollee;

    public Exam(@NotNull String subject, int score, int idEnrollee) {
        this.subject = subject;
        this.score = score;
        this.idEnrollee = idEnrollee;
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

    public int getIdEnrollee() {
        return idEnrollee;
    }

    public void setIdEnrollee(int idEnrollee) {
        this.idEnrollee = idEnrollee;
    }
}