package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Scheduling;
import com.messias.schedulingapi.repositories.SchedulingRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SchedulingServiceTest {
    @InjectMocks
    SchedulingService schedulingService;
    @Mock
    SchedulingRepository schedulingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Scheduling> schedulingList = new ArrayList<>();
        Scheduling scheduling2 = new Scheduling(1, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Scheduling scheduling1 = new Scheduling(1, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Scheduling scheduling = new Scheduling(1, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        schedulingList.add(scheduling);
        schedulingList.add(scheduling1);
        schedulingList.add(scheduling2);
        when(schedulingRepository.findAll()).thenReturn(schedulingList);

        List<Scheduling> resul = schedulingService.findAll();

        verify(schedulingRepository).findAll();
        assertNotNull(resul);
        assertEquals(schedulingList, resul);
        assertEquals(schedulingList.size(), resul.size());


    }
}