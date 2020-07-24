package com.lambdaschool.shoppingcart.controllers;

import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> userList;

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllUsers()
    {
    }

    @Test
    public void getUserById()
    {
    }

    @Test
    public void addUser()
    {
    }

    @Test
    public void deleteUserById()
    {
    }
}