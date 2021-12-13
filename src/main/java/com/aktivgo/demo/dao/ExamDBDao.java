package com.aktivgo.demo.dao;

import com.aktivgo.demo.H2Connection;
import com.aktivgo.demo.entity.ExamEntity;
import com.aktivgo.demo.model.Exam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExamDBDao implements Dao<ExamEntity> {

    private H2Connection h2Connection;

    public ExamDBDao() throws SQLException, ClassNotFoundException {
        h2Connection = new H2Connection();
        Statement statement = h2Connection.getConnection().createStatement();
        String query = "CREATE TABLE IF NOT EXISTS EXAM (idEnrollee number primary key not null, subject varchar(30) not null, score int not null);";
        statement.execute(query);
        statement.close();
    }

    public List<ExamEntity> getExamsByEnrolleeId(long idEnrollee) {
        List<ExamEntity> examList = new ArrayList<>();

        for (ExamEntity exam : getAll()) {
            if (exam.getIdEnrollee() == idEnrollee) {
                examList.add(exam);
            }
        }

        return examList;
    }

    @Override
    public long size() {
        return getAll().size();
    }

    @Override
    public Optional<ExamEntity> get(Long idEnrollee) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "SELECT FROM EXAM WHERE id = id";
            ResultSet resultSet = statement.executeQuery(query);
            String subject = resultSet.getString("subject");
            int score = resultSet.getInt("score");
            statement.close();
            return Optional.of(new ExamEntity(idEnrollee, subject, score));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<ExamEntity> getAll() {
        try {
            List<ExamEntity> examEntities = new ArrayList<>();
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "SELECT * FROM EXAM";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long idEnrollee = resultSet.getLong("idEnrollee");
                String subject = resultSet.getString("subject");
                int score = resultSet.getInt("score");
                ExamEntity examEntity = new ExamEntity(idEnrollee, subject, score);
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
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "INSERT INTO EXAM (idEnrollee, subject, score) VALUES (examEntity.getIdEnrollee(), examEntity.getSubject(), examEntity.getScore())";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "DELETE FROM EXAM WHERE id = examEntity.getIdEnrollee()";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
