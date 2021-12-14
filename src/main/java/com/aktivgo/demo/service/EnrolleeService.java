package com.aktivgo.demo.service;

import com.aktivgo.demo.dao.Dao;
import com.aktivgo.demo.entity.EnrolleeEntity;
import com.aktivgo.demo.model.Enrollee;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnrolleeService {
    private Dao enrolleeDao;

    public EnrolleeService(Dao enrolleeDao) {
        this.enrolleeDao = enrolleeDao;
    }

    public long sizeEnrollees() {
        return enrolleeDao.size();
    }

    public @NotNull List<Enrollee> getAllEnrollees() {
        return enrolleeDao.getAll();
    }

    public Enrollee getEnrollee(Long id) {
        return new Enrollee((EnrolleeEntity)(enrolleeDao.get(id).get()));
    }

    public void save(@NotNull Enrollee enrollee) {
        enrollee.setId(enrolleeDao.size());
        EnrolleeEntity enrolleeEntity = new EnrolleeEntity(enrollee);
        enrolleeDao.save(enrolleeEntity);
    }

    public List<Enrollee> getEnrollees(@NotNull List<EnrolleeEntity> enrolleeEntities) {
        List<Enrollee> enrollees = new ArrayList<>();
        for (EnrolleeEntity enrolleeEntity : enrolleeEntities) {
            enrollees.add(new Enrollee(enrolleeEntity));
        }
        return enrollees;
    }
}
