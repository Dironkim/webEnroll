package com.aktivgo.demo.dao;

import com.aktivgo.demo.H2Connection;
import com.aktivgo.demo.entity.ExamEntity;
import com.aktivgo.demo.model.Exam;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamDBDao implements Dao<ExamEntity> {

    private H2Connection h2Connection;

    public ExamDBDao(H2Connection h2Connection) throws SQLException, ClassNotFoundException {
        this.h2Connection = h2Connection;
        Statement statement = h2Connection.getConnection().createStatement();
        String query = "CREATE TABLE IF NOT EXISTS EXAM (id long generated by default as identity (start with 0) primary key not null, idEnrollee long not null, subject varchar(30) not null, score int not null);";
        statement.execute(query);
        statement.close();
        this.h2Connection = h2Connection;
    }

    public List<ExamEntity> getExamsByEnrolleeId(Long idEnrollee) {
        List<ExamEntity> examList = new ArrayList<>();

        for (ExamEntity exam : getAll()) {
            if (exam.getIdEnrollee().equals(idEnrollee)) {
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
            String query = "SELECT * FROM EXAM WHERE idEnrollee = " + idEnrollee;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            Long id = resultSet.getLong("id");
            String subject = resultSet.getString("subject");
            int score = resultSet.getInt("score");
            statement.close();
            return Optional.of(new ExamEntity(id, idEnrollee, subject, score));
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
                Long id = resultSet.getLong("id");
                Long idEnrollee = resultSet.getLong("idEnrollee");
                String subject = resultSet.getString("subject");
                int score = resultSet.getInt("score");
                ExamEntity examEntity = new ExamEntity(id, idEnrollee, subject, score);
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
    public void save(@NotNull ExamEntity examEntity) {
        try {
            String query = "INSERT INTO EXAM (id, idEnrollee, subject, score) VALUES (null, ?, ?, ?)";
            PreparedStatement preparedStatement = h2Connection.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, examEntity.getIdEnrollee());
            preparedStatement.setString(2, examEntity.getSubject());
            preparedStatement.setInt(3, examEntity.getScore());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull ExamEntity examEntity) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "DELETE FROM EXAM WHERE id = " + examEntity.getIdEnrollee();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
