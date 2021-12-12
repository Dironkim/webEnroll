package com.aktivgo.demo.model;

import com.aktivgo.demo.entity.EnrolleeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Enrollee {
    @NotNull private Long id;

    @Size(min = 2, max = 30)
    @NotNull private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull private LocalDate birthday;

    public Enrollee() {
        id = Long.MIN_VALUE;
        fullName = "";
        birthday = LocalDate.MIN;
    }

    public Enrollee(@NotNull Long id, @NotNull String fullName, @NotNull LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public Enrollee(@NotNull EnrolleeEntity enrollee) {
        this.id = enrollee.getId();
        this.fullName = enrollee.getFullName();
        this.birthday = enrollee.getBirthday();
    }

    public long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @NotNull String getFullName() {
        return fullName;
    }

    public void setFullName(@NotNull String fullName) {
        this.fullName = fullName;
    }

    public @NotNull LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotNull LocalDate birthday) {
        this.birthday = birthday;
    }

    public @NotNull String getBirthdayString() {
        return birthday.toString();
    }
}