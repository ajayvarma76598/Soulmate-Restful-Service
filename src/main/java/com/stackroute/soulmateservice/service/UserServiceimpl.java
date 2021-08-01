package com.stackroute.soulmateservice.service;


import com.stackroute.soulmateservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.soulmateservice.Exceptions.UserNotFoundException;
import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceimpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User registerUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExistsException("UserAlreadyExistException");
        }
        return userRepository.save(user);

    }



    public User updateUser(String Id,User user) throws UserNotFoundException {
        if(getUserById(Id)==null) {
            throw new UserNotFoundException("UserNotFoundException");
        } else {
            userRepository.save(user);
            return user;
        }
    }



    public boolean deleteUser(String Id) throws UserNotFoundException {
        if(getUserById(Id)==null) {
            throw new UserNotFoundException("UserNotFoundException");
        }
        userRepository.deleteById(Id);
        return true;
    }



    public User getUserById(String userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("UserNotFoundException");
        }
        return user.get();
    }




    @Override
    public List<User> listofUser() {

        return (List<User>)userRepository.findAll();
    }

    @Override
    public  List<User> searchUser(String firstName) {
        return (List<User>) userRepository.findbyName(firstName)	;
    }

    public  List<User> bygenderandage(String gender,String age) {
        return (List<User>) userRepository.findByAgeAndGender(age, gender)	;
    }
}