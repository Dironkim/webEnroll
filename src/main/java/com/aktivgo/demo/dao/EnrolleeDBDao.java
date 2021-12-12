package com.aktivgo.demo.dao;

import com.aktivgo.demo.H2Connection;
import com.aktivgo.demo.entity.EnrolleeEntity;
import com.aktivgo.demo.model.Enrollee;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrolleeDBDao implements Dao<EnrolleeEntity> {
    private final H2Connection h2Connection;

    public EnrolleeDBDao() throws SQLException {
        h2Connection = H2Connection.getH2Connection();
        Statement statement = h2Connection.getConnection().createStatement();
        String query = "CREATE TABLE IF NOT EXISTS ENROLLEE (id number primary key not null, birthday date not null, fullName varchar(30) not null);";
        EnrolleeListDao enrollees = new EnrolleeListDao();
        for (Enrollee enrollee : enrollees.getAll()) {
            query += "INSERT INTO ENROLLEE (id, birthday, fullName) VALUES (enrollee.getId(), enrollee.getBirthdayString(), enrollee.getFullName());";
        }
        statement.execute(query);
        statement.close();
    }

    @Override
    public long size() {
        return getAll().size();
    }

    @Override
    public @NotNull Optional<EnrolleeEntity> get(long id) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "SELECT FROM ENROLLEE WHERE id = id";
            ResultSet resultSet = statement.executeQuery(query);
            String fullName = resultSet.getString("fullName");
            LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
            statement.close();
            return Optional.of(new EnrolleeEntity(id, fullName, birthday));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<EnrolleeEntity> getAll() {
        try {
            List<EnrolleeEntity> enrolleeEntities = new ArrayList<>();
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "SELECT * FROM ENROLLEE";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String fullName = resultSet.getString("fullName");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                EnrolleeEntity enrolleeEntity = new EnrolleeEntity(id, fullName, birthday);
                enrolleeEntities.add(enrolleeEntity);
            }
            statement.close();
            return enrolleeEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(@NotNull EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "INSERT INTO ENROLLEE (id, birthday, fullName) VALUES (enrollee.getId(), enrollee.getBirthday(), enrollee.getFullName())";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "DELETE FROM ENROLLEE WHERE id = enrollee.getId()";
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}