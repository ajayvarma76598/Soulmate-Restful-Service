package com.stackroute.soulmateservice.controller;

import com.stackroute.soulmateservice.Exceptions.UserAlreadyExistsException;
import com.stackroute.soulmateservice.Exceptions.UserNotFoundException;
import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Usercontroller  {
	private static final Logger logger= LoggerFactory.getLogger(Usercontroller.class);
    private UserService userService;
    @Autowired
    public Usercontroller(UserService userService){
        this.userService=userService;
    }

    @PostMapping("user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        logger.info("Adding the User");
        try {
            userService.registerUser(user);
            logger.warn("user added Successfully");
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            logger.error("user is already exist:" +user.getId());
            logger.warn("Conflict");
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user){
        logger.info("Updating the User");
        try {
            if(userService.updateUser(id, user)!=null) {
                logger.warn("user Updated Successfully");
                return new ResponseEntity(HttpStatus.OK);
            } else {
                logger.error("User Not found"+user.getId());
                logger.warn("Not Found");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (UserNotFoundException e) {
            logger.error("Something Went Wrong:" +e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        logger.info("Deleting Executed");
        logger.warn("Id" +id);
        try {
            if(userService.deleteUser(id)) {
                logger.warn("User is deleted Successfully:" +id);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                logger.error("User Not Found:"+id );
                logger.warn("Not Found");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (UserNotFoundException e) {
            logger.error("Something Went Wrong:" +e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id){
        logger.info("Getting User by id");
        User user;
        try {

            user = userService.getUserById(id);
        } catch (UserNotFoundException e) {
            logger.error("User Not Found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(user!=null) {
            logger.warn("User is:" +id);
            return new ResponseEntity(user,HttpStatus.OK);
        } else {
            logger.error("Check it again for user");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/user/list")
    public ResponseEntity<?> listofUser(){
        logger.info("Getting User list");
        logger.warn("User list");
        return new ResponseEntity(userService.listofUser(),HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> searchUser( @PathVariable String firstName){
        logger.info("Searching User");
        logger.warn("User:"+firstName);
        return new ResponseEntity(userService.searchUser(firstName),HttpStatus.OK);

    }
    @GetMapping("/{gender}/{age}")
    public ResponseEntity<?> genderage( @PathVariable String gender,@PathVariable String age){
        logger.info("Searching User by gender and age");
        logger.warn("User by gender & age");
        return new ResponseEntity(userService.bygenderandage(gender, age),HttpStatus.OK);

    }


}