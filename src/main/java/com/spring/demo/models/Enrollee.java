package com.spring.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNull
public class Enrollee {
    private Long id;
    @Size(min = 2, max = 30)
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


}