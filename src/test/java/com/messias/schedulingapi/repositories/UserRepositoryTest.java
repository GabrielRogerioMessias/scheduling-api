package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    @Test
    void findByUsernameSucess() {
        String username = "gmessias";
        User user = new User(1,"Gabriel R Messias", "gmessias", "q1w2e3r4", true, true, true, true);
        // Persiste a entidade usando o método persist
        this.entityManager.merge(user);
        // Realiza a busca
        User userResponse = userRepository.findByUsername(username);
        assertThat(userResponse != null);

    }

    
}