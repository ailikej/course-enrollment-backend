package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.UserCourse;
import com.mycompany.myapp.repository.CourseRepository;
import com.mycompany.myapp.repository.UserCourseRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.dto.CourseDto;
import com.mycompany.myapp.service.mapper.CourseMapper;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private UserCourseRepository userCourseRepository;
    private CourseMapper courseMapper;


    // 以下内容为 javadoc  因为是public method
    /**
     * Enroll course by courseName and userName
     * 1. check if corresponding course and user exist in DB. If not, throw exception
     * 2  check if corresponding user-course pair not exist in DB(user_course table). If exists, throw exception
     * 3  save user-course pair.
     * @param courseName
     * @param userName
     * **/

    public void enrollCourse(String courseName, String userName) {
        UserCourse userCourse = getUserCourse(courseName, userName);

        userCourseRepository.findFirstByUserAndCourse(userCourse.getUser(), userCourse.getCourse())
            .ifPresent(exsitCourse -> {
                throw new IllegalArgumentException("Course already enrolled!");
            });

        userCourseRepository.save(userCourse);
    }

    private UserCourse getUserCourse(String courseName, String userName) {
        User user = userRepository.findOneByLogin(userName)
            .orElseThrow(() -> new UsernameNotFoundException("User name not found!"));
        Course course = courseRepository.findFirstByCourseName(courseName)
            .orElseThrow(() -> new IllegalArgumentException(("Course name not valid!")));

        return UserCourse.builder()
            .user(user)
            .course(course)
            .build();

        }


    /**
     * Get all courses from `course` table.
     * Convert from Course to CourseDto and return.
     * @return all course dtos retreived from database
     */

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()  // java 需要stream 才能调用 map， filter
            .map(course -> courseMapper.convertToCourseDto(course))  // 把list of courses每一个course 转化成courseDto
            .collect(Collectors.toList());

    }

    /**
     * Get enrolled courses by userName
     * 1. Get user by userName
     * 2. Get enrolled courses by user
     * 3. convert and return
     * @param userName
     * @return all enrolled courses for corresponding user
     */
    public List<CourseDto> getEnrolledCourses(String userName) {
        // Get user by userName
        User user = userRepository.findOneByLogin(userName)
            .orElseThrow(() -> new UsernameNotFoundException("User name not found!"));

        // Get enrolled courses by user
        List<UserCourse> userCourses = userCourseRepository.findAllByUser(user);

        // convert and return
        return userCourses.stream()
            .map(userCourse -> userCourse.getCourse())
            .map(course -> courseMapper.convertToCourseDto(course))
            .collect(Collectors.toList());
    }


    /**
     *  Drop course by courseName and userName
     *  1. Get UserCourse by courseName and userName
     *  2. Delete UserCourse by course and user
     * @param courseName
     * @param userName
     */
    public void dropCourse(String courseName, String userName) {
        UserCourse userCourse = getUserCourse(courseName, userName);
        userCourseRepository.deleteByUserAndCourse(userCourse.getUser(), userCourse.getCourse());
    }
}
