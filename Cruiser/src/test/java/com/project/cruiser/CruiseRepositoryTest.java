package com.project.cruiser;

import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.RoleType;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.CruiseRepository;
import com.project.cruiser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CruiseRepositoryTest {

    @Autowired
    private CruiseRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateCruise() {
        Cruise cruise = new Cruise();
        cruise.setPassCapacity(1355);
        cruise.setRoute("Italy - Greece - France - Italy");
        cruise.setStart(LocalDateTime.of(2021, 10, 13, 12, 35));
        cruise.setEnd(LocalDateTime.of(2021, 11, 1, 18, 15));

        Cruise savedCruise = repo.save(cruise);
        Cruise existCruise = entityManager.find(Cruise.class, savedCruise.getId());

        assertThat(existCruise.getRoute()).isEqualTo(cruise.getRoute());

    }

}
