package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//这里不需要@ Repos   因为@EnableJpaRepositories("com.mycompany.myapp.repository") 已经了
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findFirstByCourseName(String courseName);
}
