package com.mycompany.myapp.config;

import com.mycompany.myapp.service.mapper.CourseMapper;
import com.mycompany.myapp.utils.UserUtility;
import liquibase.pro.packaged.C;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean  // @Bean 是标在函数上的
    public UserUtility userUtility(){
        return new UserUtility();
    }

    @Bean
    public CourseMapper courseMapper(){
        return new CourseMapper();
    }

}
