package com.spring.demo.dao;

import com.spring.demo.mocks.EnrolleeMockData;
import com.spring.demo.models.Enrollee;

import java.util.List;
import java.util.Optional;

public class EnrolleeListDao implements Dao<Enrollee>{
    private final List<Enrollee> enrollees = EnrolleeMockData.createMockEnrollees();
    @Override
    public long size() {
        return enrollees.size();
    }
    @Override
    public Optional<Enrollee> get(Long id) {
        return enrollees.stream()
                .filter(enrollee -> enrollee.getId().equals(id))
                .findFirst();
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
