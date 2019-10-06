package com.geekbrains.septembermarket;

import com.geekbrains.septembermarket.entities.User;
import com.geekbrains.septembermarket.repositories.UserRepository;
import com.geekbrains.septembermarket.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        Mockito.doReturn(new User("userJohn", "J1o2h3n4s5o6n7", "John", "Johnson", "johnson@gmail.com"))
                .when(userRepository)
                .findOneByUsername("userJohn");
        User userJohn = userService.findByUsername("userJohn");
        Assert.assertNotNull(userJohn);
        Mockito.verify(userRepository, Mockito.times(1)).findOneByUsername(ArgumentMatchers.eq("userJohn"));
        Mockito.verify(userRepository, Mockito.times(1)).findOneByUsername(ArgumentMatchers.any(String.class));
    }
}
