package com.stackroute.soulmateservice.controller;

import com.stackroute.soulmateservice.model.User;
import com.stackroute.soulmateservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @Autowired
    MockMvc mockMvc;
    @Mock
    private UserService userService;
    private User user;
    private List<User> userList;

    @InjectMocks
    private Usercontroller usercontroller;

    @BeforeEach
    public void setUp(){
        user=new User("1","Abhsihek","Chauhan","Male","22");
        mockMvc= MockMvcBuilders.standaloneSetup(usercontroller).build();
    }

    @Test
    public void saveUser() throws Exception {
        when(userService.registerUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(userService,times(1)).registerUser(any());
    }

    @Test
    public void updateUser() throws Exception {
        user.setId("A5");
        when(userService.updateUser(eq(user.getId()), any())).thenReturn(user);
        mockMvc.perform(put("/api/v1/user/A5")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk());




    }


    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }








}