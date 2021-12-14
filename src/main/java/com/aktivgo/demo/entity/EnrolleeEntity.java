package com.aktivgo.demo.entity;

import com.aktivgo.demo.model.Enrollee;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "ENROLLEE")
public class EnrolleeEntity {
    @Id
    @GeneratedValue
    @Autowired
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FULLNAME", length = 64, nullable = false)
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;

    public EnrolleeEntity() {

    }

    public EnrolleeEntity(@NotNull Long id, @NotNull String fullName, @NotNull Date birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public EnrolleeEntity(@NotNull Enrollee enrollee) {
        this.id = enrollee.getId();
        this.fullName = enrollee.getFullName();
        this.birthday = enrollee.getBirthday();
    }

    public @NotNull Long getId() {
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
