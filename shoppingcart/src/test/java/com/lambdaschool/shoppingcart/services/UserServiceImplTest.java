package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingcartApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingcartApplication.class)
public class UserServiceImplTest
{
    @Autowired
    UserService userService;

    @org.junit.Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(3,
            userService.findAll()
                .size());
    }

    @Test
    public void findUserById()
    {
        assertEquals("barnbarn",
            userService.findUserById(1)
                .getUsername());
    }

    @org.junit.Test
    public void delete()
    {
    }

    @org.junit.Test
    public void findByName()
    {
    }

    @org.junit.Test
    public void save()
    {
    }

    @org.junit.Test
    public void update()
    {
    }
}