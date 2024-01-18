package com.spring.demo.entities;

import com.spring.demo.models.Enrollee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ENROLLEE")
public class EnrolleeEntity {

    @Id
    @GeneratedValue
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FULLNAME", length = 64, nullable = false)
    private String fullName;

    //@Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private java.sql.Date birthday;
    public EnrolleeEntity(Enrollee enrollee) {
        setId(enrollee.getId());
        setBirthday(transformDate(enrollee.getBirthday()));
        setFullName(enrollee.getFullName());
    }

    public static java.sql.Date transformDate(java.util.Date date) {
        return new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());
    }
}
