package com.project.cruiser;

import com.project.cruiser.entity.RoleType;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("admin");
        user.setFirstName("Vlad");
        user.setLastName("Herasimov");
        user.setRole(RoleType.ADMIN);

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());

    }
}
