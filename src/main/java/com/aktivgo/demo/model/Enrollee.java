package com.aktivgo.demo.model;

import com.aktivgo.demo.entity.EnrolleeEntity;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Enrollee {
    private long id;
    private String fullName;
    private LocalDate birthday;

    public Enrollee(int id, @NotNull String fullName, @NotNull LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public Enrollee(EnrolleeEntity enrollee) {
        this.id = enrollee.getId();
        this.fullName = enrollee.getFullName();
        this.birthday = enrollee.getBirthday();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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