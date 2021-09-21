package com.project.cruiser.services;

import com.project.cruiser.entity.RoleType;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void saveTest() {
        User user = new User();
        user.setPassword("password");
        boolean isUserCreated = userService.save(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertTrue(CoreMatchers.is(user.getRole()).matches(RoleType.USER));
        Assert.assertEquals((Integer) 0, user.getMoneyAmount());

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    public void saveFailedTest() {
        User user = new User();
        user.setPassword("password");
        user.setEmail("mail@mail.com");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByEmail("mail@mail.com");

        boolean isUserCreated = userService.save(user);

        Assert.assertFalse(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }

}