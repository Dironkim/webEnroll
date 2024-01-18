package com.spring.demo.mocks;

import com.spring.demo.models.Enrollee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnrolleeMockData {

    public static List<Enrollee> createMockEnrollees() {
        List<Enrollee> enrollees = new ArrayList<>();
        enrollees.add(new Enrollee(0L, "Hkkjhr Doe", new Date()));
        enrollees.add(new Enrollee(1L, "John Doe", new Date()));
        enrollees.add(new Enrollee(2L, "Jane Doe", new Date()));
        enrollees.add(new Enrollee(3L, "Fgddfh Doe", new Date()));
        enrollees.add(new Enrollee(4L, "Jdfhhde Doe", new Date()));
        enrollees.add(new Enrollee(5L, "BaJdffohn Doe", new Date()));

        return enrollees;
    }
}
