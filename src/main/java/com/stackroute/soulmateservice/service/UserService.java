package com.stackroute.soulmateservice.service;

import com.stackroute.soulmateservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.soulmateservice.Exceptions.UserNotFoundException;
import com.stackroute.soulmateservice.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user) throws UserAlreadyExistsException;
    User updateUser(String Id,User user) throws UserNotFoundException;
    boolean deleteUser(String userId) throws UserNotFoundException;
    User getUserById(String userId) throws UserNotFoundException;
    List<User> listofUser();
    List<User> searchUser(String firstName);
    List<User> bygenderandage(String gender,String age);

}

