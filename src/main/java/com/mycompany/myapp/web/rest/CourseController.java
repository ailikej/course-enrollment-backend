package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.CourseService;
import com.mycompany.myapp.service.dto.CourseDto;
import com.mycompany.myapp.utils.UserUtility;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Bean 声明
@RestController     // @Controller + @ResponseBody      @Controller是@Component 变种
@AllArgsConstructor
@RequestMapping(path = "/api")    // 改path ，完成authandicate 权限
public class CourseController {

    private UserUtility userUtility;  // instance
    private CourseService courseService;

    // Ioc 和DI 的关系
    // 通过DI 实现IoC
    // DI 是一种手段

    // DI constructor
//    public CourseController(UserUtility userUtility){
//        this.userUtility = userUtility;
//    }    // 删掉， 因为加了@AllArgsConstructor

    // POST: /course/enrollment
    // Request body: {courseName}
    // Response body: null
    // Header: User token

//    @PostMapping(path = "/course/enrollment/{courseName}")
//    public void enrollCourse(@PathVariable String courseName){
    @PostMapping(path = "/course/enrollment")
    public void enrollCourse(@RequestBody CourseDto courseDto){
        String courseName = courseDto.getCourseName();
        String userName = userUtility.getUserName();

        courseService.enrollCourse(courseName, userName);  // contoller本身不处理　业务逻辑，courseService处理
    }

    // GET: /course
    // Request body: null
    // Response body: [course object]
    // Header: Need authorized
    @GetMapping(path = "/course")
    public List<CourseDto> getAllCourses(){
        return courseService.getAllCourses();
    }
    // GET: /course
    // Request body: null
    // Response body: [course object]
    // Header: Need authorized
    @GetMapping(path = "/course/enrollment")
    public List<CourseDto> getEnrolledCourses(){
        String userName = userUtility.getUserName();
        return courseService.getEnrolledCourses(userName);
    }


    // DELETE: /course/enrollment
    // Request body:  {courseName}
    // Response body: void
    // Header: User token
    @DeleteMapping(path = "/course/enrollment")
    public void dropCourse(@RequestBody CourseDto courseDto){
        String courseName = courseDto.getCourseName();
        String userName = userUtility.getUserName();
        courseService.dropCourse(courseName, userName);
    }

}

//    private String getUserName(){
//        String userName;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(principal instanceof UserDetails) {
//            userName = ((UserDetails)principal).getUsername();
//        }else {
//            userName = principal.toString();
//        }
//        return userName;


