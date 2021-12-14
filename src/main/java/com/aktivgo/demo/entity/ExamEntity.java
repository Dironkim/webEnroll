package com.aktivgo.demo.entity;

import com.aktivgo.demo.model.Exam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXAM")
public class ExamEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ID_ENROLLEE", nullable = false)
    private Long idEnrollee;

    @Column(name = "SUBJECT", length = 64, nullable = false)
    private String subject;

    @Column(name = "SCORE", nullable = false)
    private int score;

    public ExamEntity() {

    }

    public ExamEntity(Long id, @NotNull Long idEnrollee, @NotNull String subject, int score) {
        this.id = id;
        this.idEnrollee = idEnrollee;
        this.subject = subject;
        this.score = score;
    }

    public ExamEntity(@NotNull Exam exam) {
        this.id = exam.getId();
        this.idEnrollee = exam.getIdEnrollee();
        this.subject = exam.getSubject();
        this.score = exam.getScore();
    }

    public @NotNull Long getIdEnrollee() {
        return idEnrollee;
    }

    public void setIdEnrollee(@NotNull Long idEnrollee) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
