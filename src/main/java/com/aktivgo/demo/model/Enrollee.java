package com.aktivgo.demo.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class Enrollee {
    private int id;
    private String fullName;
    private LocalDate birthday;

    public Enrollee(int id, @NotNull String fullName, @NotNull LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}