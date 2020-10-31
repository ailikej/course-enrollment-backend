package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// Hibernate
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    Optional<UserCourse> findFirstByUserAndCourse(User user, Course course);

    List<UserCourse> findAllByUser(User user);

    @Transactional  // 事务。 确保存在 1。得到user 2。得到course 3。删    All or None
    void deleteByUserAndCourse(User user, Course course);
}
