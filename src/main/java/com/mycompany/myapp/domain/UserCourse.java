package com.mycompany.myapp.domain;

import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="user_course")
@Data //getter setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCourse {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)  // 绑Auto Increment
    @Column(name = "id")
    private Long id;

    // 绑定foreign key
    @JoinColumn(name ="user_id", referencedColumnName = "id"/*jhi_user表的id */)
    @ManyToOne
    private User user;  // user 是user_course的外键,
                        // user是整个User object.
                        // 这里不是Long，是User，来自User domain,
                        // 多对一，因为course 有很多行，这里指向id 一行

    @JoinColumn(name ="course_id", referencedColumnName = "id"/*course表的id */)
    @ManyToOne
    private Course course;   // 同上， 这个course object是来自domain Course, 指向id 那一行
}
