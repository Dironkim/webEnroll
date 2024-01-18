package com.spring.demo.service;

import com.spring.demo.dao.Dao;
import com.spring.demo.entities.EnrolleeEntity;
import com.spring.demo.models.Enrollee;

import java.util.ArrayList;
import java.util.List;

public class EnrolleeService {

    private Dao enrolleeDao;

    public EnrolleeService(Dao enrolleeDao) {
        this.enrolleeDao = enrolleeDao;
    }

    public long sizeEnrollees() {
        return  enrolleeDao.size();
    }

    public List<Enrollee> getAllEnrollees() {
        return getEnrollees(enrolleeDao.getAll());
    }

    public Enrollee getEnrollee (long id) {
        return new Enrollee((EnrolleeEntity) enrolleeDao.get(id).get());
    }

    public void save(Enrollee enrollee) {
        enrollee.setId(enrolleeDao.size());
        EnrolleeEntity enrolleeEntity = new EnrolleeEntity(enrollee);
        enrolleeDao.save(enrolleeEntity);
    }
    public List<Enrollee> getEnrollees(List<EnrolleeEntity> enrolleeEntitys) {
        List<Enrollee> enrollees = new ArrayList<>();
        for (EnrolleeEntity enrolleeEntity: enrolleeEntitys) {
            enrollees.add(new Enrollee(enrolleeEntity));
        }
        return enrollees;
    }
}
