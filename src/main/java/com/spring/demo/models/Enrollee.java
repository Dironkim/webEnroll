package com.spring.demo.models;

import com.spring.demo.entities.EnrolleeEntity;
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
@Size(min = 2, max = 30)
public class Enrollee {
    private Long id;
    @Size(min = 2, max = 30)
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


    public Enrollee(EnrolleeEntity enrollee) {
        setId(enrollee.getId());
        setBirthday(enrollee.getBirthday());
        setFullName(enrollee.getFullName());
    }
    public boolean notNull() {
        return fullName != null && birthday != null;
    }
}