package com.mycompany.myapp.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//@Component  标在类上的  因为UserUtility已经被@bean 了， 这里就注释掉
public class UserUtility {
    public String getUserName(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        }else {
            userName = principal.toString();
        }
        return userName;
    }
}
