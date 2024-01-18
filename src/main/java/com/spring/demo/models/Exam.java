package com.spring.demo.models;

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

}