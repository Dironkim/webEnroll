package com.spring.demo.dao;

import com.spring.demo.dao.Dao;
import com.spring.demo.dao.H2Connection;
import com.spring.demo.entities.EnrolleeEntity;
import com.spring.demo.entities.ExamEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamDBDao implements Dao<ExamEntity> {
    H2Connection h2Connection;

    public ExamDBDao() throws SQLException, ClassNotFoundException {
        h2Connection = H2Connection.getH2Connection();
        Statement statement = h2Connection.getConnection().createStatement();

        String s = "CREATE TABLE IF NOT EXISTS EXAM " +
                "(idEnrollee INT NOT NULL, " +
                " score INT NOT NULL, " +
                " subject VARCHAR(100) NOT NULL, " +
                " CONSTRAINT FK_idEnrollee FOREIGN KEY (idEnrollee) REFERENCES ENROLLEE(id));";

        statement.execute(s);

        // Добавление данных
        /*s = "INSERT INTO EXAM (subject, score, idEnrollee) VALUES ('Математика', 78, 0);";
        statement.execute(s);
        s = "INSERT INTO EXAM (subject, score, idEnrollee)\n" +
                "VALUES ('Русский язык', 94, 0);";
        statement.execute(s);
        s = "INSERT INTO EXAM (subject, score, idEnrollee)\n" +
                "VALUES ('Английский язык', 82, 0);";
        statement.execute(s);
        s = "INSERT INTO EXAM (subject, score, idEnrollee)\n" +
                "VALUES ('Информатика', 90, 0);";*/
        statement.execute(s);
        statement.close();
    }

    @Override
    public long size() {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from EXAM");
            int counter = 0;
            while (resultSet.next()) {
                counter++;
            }
            statement.close();
            return counter;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Optional<ExamEntity> get(Long id) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from EXAM");
            while (resultSet.next()) {
                if (resultSet.getLong("id") == id) {
                    ExamEntity examEntity = new ExamEntity();
                    examEntity.setIdEnrollee((long) resultSet.getInt("idEnrollee"));
                    examEntity.setScore(resultSet.getInt("score"));
                    examEntity.setSubject(resultSet.getString("subject"));
                    statement.close();
                    return Optional.of(examEntity);
                }
            }
        } catch (SQLException thrown) {
            thrown.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ExamEntity> getAll() {
        try {
            List<ExamEntity> examEntities = new ArrayList<>();
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from EXAM");
            while (resultSet.next()) {
                ExamEntity examEntity = new ExamEntity();
                examEntity.setIdEnrollee((long) resultSet.getInt("idEnrollee"));
                examEntity.setScore(resultSet.getInt("score"));
                examEntity.setSubject(resultSet.getString("subject"));
                examEntities.add(examEntity);
            }
            statement.close();
            return examEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            String s = String.format("insert into EXAM (subject, score, idEnrollee) values ('%s', %s, %s)",
                    examEntity.getSubject(), examEntity.getScore(), examEntity.getIdEnrollee());
            statement.execute(s);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            statement.executeQuery(String.format("delete * from EXAM where id = %s", examEntity.getIdEnrollee()));
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}