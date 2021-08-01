package com.stackroute.soulmateservice.Repository;

import com.stackroute.soulmateservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user;
    private User user1;

    @BeforeEach
    public void setUp()throws Exception {
        user = new User("1", "Abhishek", "Chauhan", "Male", "22");
        user1 = new User("2", "rishab", "Rajput", "Male", "22");
    }
    @Test
    public void givenSoulmateToSaveShouldReturnSoulmate(){
        this.userRepository.save(user);
        User user1=this.userRepository.findById(user.getId()).get();
        assertNotNull(user1);
        assertEquals(user1.getFirstName(),user.getFirstName());

    }

    @Test
    public void getUserByIdTest() {
        this.userRepository.save(user);
        User fetcheduser = userRepository.findById("2").get();
        assertEquals(user1.getId(),fetcheduser.getId());

    }


}
