package com.sharpframework.test;

import com.sharpframework.test.model.User;
import com.sharpframework.test.repository.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean(UserDao.class);


        //add user
        int userId = 2;  //sharding key

        User userEntity = new User();
        userEntity.setId(userId);
        userEntity.setUsername("robin");

        boolean addUserSuccess = userDao.addUser(userId, userEntity);
        if (addUserSuccess) {
            System.out.println("add user success");
        } else {
            System.out.println("add user fail");
        }


        User user = userDao.getUserById(userId);
        if (user != null) {
            System.out.println(user.getId() + "-" + user.getUsername());
        } else {
            System.out.println("user null");
        }


    }
}
