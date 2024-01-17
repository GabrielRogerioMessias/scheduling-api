package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.SchedulingInfo;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.text.StyledEditorKit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SchedulingInfoRepositoryTest {
    @Autowired
    SchedulingInfoRepository schedulingInfoRepository;

    @Autowired
    TestEntityManager entityManager;


    @Test
    @Order(1)
    void findByHasSchedulingSuccess() {
        SchedulingInfo schedulingInfo = new SchedulingInfo(1, true, "ADMISSINAL");
        schedulingInfoRepository.save(schedulingInfo);
        Boolean result = schedulingInfoRepository.findByHasScheduling(1);
        assertTrue(result);
    }
    
}