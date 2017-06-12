package com.sharpframework.test.mapping;


import com.sharpframework.test.model.User;

public interface UserMapper {

    int addUser(User record);

    User getUserById(Integer id);

}