package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Scheduling;
import com.messias.schedulingapi.repositories.SchedulingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Scheduling scheduling1 = new Scheduling(2, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
        Scheduling scheduling = new Scheduling(3, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());
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

    @Test
    void findByIdCase1() {
        Integer idScheduling = 1;
        Scheduling scheduling = new Scheduling(1, LocalDate.now(), LocalDateTime.now(), LocalDateTime.now());

        when(schedulingRepository.findById(idScheduling)).thenReturn(Optional.of(scheduling));
        Scheduling result = schedulingService.findById(idScheduling);

        assertNotNull(result);
        assertEquals(scheduling.getStartOfService(), result.getStartOfService());
        assertEquals(scheduling, result);
        verify(schedulingRepository).findById(idScheduling);
    }

}