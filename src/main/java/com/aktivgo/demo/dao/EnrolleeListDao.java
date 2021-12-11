package com.aktivgo.demo.dao;

import com.aktivgo.demo.model.Enrollee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrolleeListDao implements Dao<Enrollee> {
    private List<Enrollee> enrollees = new ArrayList<>();

    public EnrolleeListDao() {
        enrollees.add(new Enrollee(1, "Кочкин Владислав", LocalDate.parse("2001-06-01")));
        enrollees.add(new Enrollee(2, "Ветлугаев Павел", LocalDate.parse("2000-11-06")));
        enrollees.add(new Enrollee(3, "Кинаш Роман", LocalDate.parse("2001-12-30")));
    }

    @Override
    public int size() {
        return enrollees.size();
    }

    @Override
    public Optional<Enrollee> get(int id) {
        return Optional.of(enrollees.get(id));
    }

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    @Override
    public void save(Enrollee enrollee) {
        enrollees.add(enrollee);
    }

    @Override
    public void delete(Enrollee enrollee) {
        enrollees.remove(enrollee);
    }

}
