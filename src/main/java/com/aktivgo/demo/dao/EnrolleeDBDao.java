package com.aktivgo.demo.dao;

import com.aktivgo.demo.H2Connection;
import com.aktivgo.demo.entity.EnrolleeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrolleeDBDao implements Dao<EnrolleeEntity> {

    private H2Connection h2Connection;

    @Autowired
    public EnrolleeDBDao(H2Connection h2Connection) throws SQLException, ClassNotFoundException {
        this.h2Connection = h2Connection;
        Statement statement = h2Connection.getConnection().createStatement();
        String query = "CREATE TABLE IF NOT EXISTS ENROLLEE (id long primary key not null, birthday date not null, fullName varchar(30) not null);" +
                "INSERT INTO ENROLLEE (id, fullName, birthday) VALUES (0, 'Кочкин Владислав Романович', '2001-06-01');";
        statement.execute(query);
        statement.close();
        this.h2Connection = h2Connection;
    }

    @Override
    public long size() {
        return getAll().size();
    }

    @Override
    public @NotNull Optional<EnrolleeEntity> get(Long id) {
        try {
            Statement statement = h2Connection.getConnection().createStatement();
            String query = "SELECT * FROM ENROLLEE WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            Long id1 = resultSet.getLong("id");
            String fullName = resultSet.getString("fullName");
            Date birthday = resultSet.getDate("birthday");
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
                Date birthday = resultSet.getDate("birthday");
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
            String query = "INSERT INTO ENROLLEE (id, birthday, fullName) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = h2Connection.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, enrollee.getId());
            preparedStatement.setDate(2, java.sql.Date.valueOf(enrollee.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            preparedStatement.setString(3, enrollee.getFullName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(@NotNull EnrolleeEntity enrollee) {
        try {
            String query = "DELETE FROM ENROLLEE WHERE id = ?";
            PreparedStatement preparedStatement = h2Connection.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, enrollee.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}