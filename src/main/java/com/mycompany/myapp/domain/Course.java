package com.mycompany.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)  // 绑AI
    @Column(name = "id")   // 严格跟SQL 名字一样 "id"
    private Long id;        // 这个随便取名

    @Column(name = "course_name")
    private String  courseName;

    @Column(name = "course_location")
    private String courseLocation;

    @Column(name = "course_content")
    private String courseContent;

    @Column(name = "teacher_id")
    private Long teacherId;

}
