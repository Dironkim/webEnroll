package com.spring.demo.entities;

import com.spring.demo.models.Enrollee;
import com.spring.demo.models.Exam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ENROLLEE")
public class ExamEntity {

    @Column(name = "SUBJECT", length = 64, nullable = false)
    private String subject;

    @Column(name = "SCORE", nullable = false)
    private Integer score;

    @Id
    @GeneratedValue
    @Column(name = "IDENROLLEE", nullable = false)
    private Long idEnrollee;

    public ExamEntity(Exam exam) {
        setSubject(exam.getSubject());
        setScore(exam.getScore());
        setIdEnrollee(exam.getIdEnrollee());
    }
}