package com.stackroute.soulmateservice.service;


import com.stackroute.soulmateservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.soulmateservice.Exceptions.UserNotFoundException;
import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    private UserServiceimpl userServiceImplmentation;

    @Test
    public void givenUser() throws UserAlreadyExistsException {
        User user=new User("1","Abhsihek","Chauhan","Male","22");
        when(userRepository.save(any())).thenReturn(user);

        userServiceImplmentation.registerUser(user);
        verify(userRepository,times(1)).save(any());
    }
    @Test
    public void getAllUser(){
        User user=new User("1","Abhsihek","Chauhan","Male","22");
        userRepository.save(user);
        List<User> userList=userServiceImplmentation.listofUser();
        assertEquals(userList,userList);
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findAll();
    }
    @Test
    public void deleteUser() throws UserNotFoundException {
        User user=new User("1","Abhsihek","Chauhan","Male","22");

        user.setFirstName("Abhishek");
        user.setId("1");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userServiceImplmentation.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }








}