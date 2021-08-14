package com.project.cruiser;

import com.project.cruiser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CruiserApplicationTests {

    @Autowired
    private UserRepository repo;

    @Test
    void contextLoads() {
    }

}
