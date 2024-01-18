package com.spring.demo.dao;

import com.spring.demo.entities.EnrolleeEntity;
import com.spring.demo.models.Enrollee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.spring.demo.entities.EnrolleeEntity.transformDate;

public class EnrolleeDBDao implements Dao<EnrolleeEntity>{
    private H2Connection h2Connection;

    public EnrolleeDBDao() throws SQLException, ClassNotFoundException {
        h2Connection = H2Connection.getH2Connection();
        Statement statement = h2Connection.getConnection().createStatement();
        String s = "CREATE TABLE IF NOT EXISTS ENROLLEE" +
                "(id number primary key not null," +
                " birthday date not null, " +
                " fullName varchar(30) not null );";
        //        "INSERT INTO ENROLLEE (id, birthday, fullName)\n" +
        //        "VALUES (0, '2003-03-23', 'Иванов Иван Иванович');";
        // добавьте свои записи
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
                    executeQuery("select * from ENROLLEE");
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
    public Optional<EnrolleeEntity> get(Long id) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from ENROLLEE");
            while (resultSet.next()) {
                if (resultSet.getLong("id") == id) {
                    EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
                    enrolleeEntity.setId((long) resultSet.getInt("id"));
                    enrolleeEntity.setBirthday(resultSet.getDate("birthday"));
                    enrolleeEntity.setFullName(resultSet.getString("fullName"));
                    statement.close();
                    return Optional.of(enrolleeEntity);
                }
            }
        } catch (SQLException thrown) {
            thrown.printStackTrace();
        }
        return null;
    }

    @Override
    public List< EnrolleeEntity > getAll() {
        try {
            List<EnrolleeEntity> enrolleeEntities = new ArrayList<>();
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            ResultSet resultSet = statement.
                    executeQuery("select * from ENROLLEE");
            while (resultSet.next()) {
                Long id= resultSet.getLong("id");
                String fullName = resultSet.getString("fullName");
                Date birthday = resultSet.getDate("birthday");
                EnrolleeEntity enrolleeEntity = new EnrolleeEntity();
                enrolleeEntity.setId(id);
                enrolleeEntity.setFullName(fullName);
                enrolleeEntity.setBirthday(transformDate(birthday));
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
    public void save(EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            String s = String.format("insert into ENROLLEE values (%s, '%s', '%s')",
                    enrollee.getId(), new java.sql.Date(enrollee.getBirthday().getTime()), enrollee.getFullName());
            statement.execute(s);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(EnrolleeEntity enrollee) {
        try {
            Statement statement = h2Connection.
                    getConnection().
                    createStatement();
            statement.executeQuery(String.format("delete * from ENROLLEE where id = %s", enrollee.getId()));
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
