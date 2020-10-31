package com.mycompany.myapp.service.dto;
import liquibase.pro.packaged.B;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO - Data transfer object
// 负责前后端交流的数据格式

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private String courseName;
    private String courseLocation;
    private String courseContent;
    private Integer teacherId;


}

