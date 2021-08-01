package com.stackroute.soulmateservice.Repository;

import com.stackroute.soulmateservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query("{'name':?0}")
    List<User> findbyName(String firstName);

    @Query("{'gender':?0,'age':?1}")
    List<User> findByAgeAndGender(String gender,String age);
}
