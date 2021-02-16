package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.models.Role;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.models.UserRoles;
import com.lambdaschool.shoppingcart.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        userList = new ArrayList<>();

        Role r1 = new Role("admin");
        r1.setRoleid(1);
        Role r2 = new Role("user");
        r1.setRoleid(2);
        Role r3 = new Role("data");
        r1.setRoleid(3);

        // admin, data, user
        User u1 = new User("admin",
            "password",
            "admin@lambdaschool.local");
        u1.setUserid(11);
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));

        userList.add(u1);

        // data, user
        User u2 = new User("cinnamon",
            "1234567",
            "cinnamon@lambdaschool.local");
        u2.setUserid(12);
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getRoles()
            .add(new UserRoles(u2,
                r3));
        userList.add(u2);

        // user
        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local");
        u3.setUserid(13);
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        userList.add(u3);

        User u4 = new User("puttat",
            "password",
            "puttat@school.lambda");
        u4.setUserid(14);
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));
        userList.add(u4);

        User u5 = new User("misskitty",
            "password",
            "misskitty@school.lambda");
        u5.setUserid(15);
        u5.getRoles()
            .add(new UserRoles(u5,
                r2));
        userList.add(u5);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllUsers() throws Exception
    {
        String apiUrl = "/users/users";
        Mockito.when(userService.findAll())
            .thenReturn(userList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
            .accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb)
            .andReturn(); // this can throw an exception
        String tr = r.getResponse()
            .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals(er,
            tr);
    }

    @Test
    public void getUserById() throws Exception
    {
        String apiUrl = "/users/user/{userid}";

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl,
            "1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addUser() throws Exception
    {
        String apiUrl = "/users/user";

        User newUser = new User("cinnamon",
            "$2y$12$5EhoDOj2oGGEhmW8UFwh6e5an6WZ/IaoDPCux0oN4x4pwLm0pj6lq",
            "SYSTEM");
        newUser.setUserid(2);

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(newUser);

        Mockito.when(userService.save(any(User.class)))
            .thenReturn(newUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(apiUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(userString);

        ResultActions mvcResult = mockMvc.perform(requestBuilder)
            .andExpect(status().isCreated())
            .andDo(MockMvcResultHandlers.print());
        String testResult = mvcResult.toString();

        System.out.println("Actual: " + testResult);
    }

    @Test
    public void deleteUserById()
    {
    }
}