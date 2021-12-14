package com.aktivgo.demo.model;

import com.aktivgo.demo.entity.EnrolleeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class Enrollee {
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String fullName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public Enrollee() {
        id = 0L;
        fullName = "";
        birthday = new Date();
    }

    public Enrollee(@NotNull Long id, @NotNull String fullName, @NotNull Date birthday) {
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

    public @NotNull Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@NotNull Date birthday) {
        this.birthday = birthday;
    }

    public @NotNull String getBirthdayString() {
        return birthday.toString();
    }
}