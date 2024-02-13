package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Scheduling;
import com.messias.schedulingapi.repositories.SchedulingRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
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

    @Test
    void findByIdCase2() {
        Integer idScheduling = 1;
        when(schedulingRepository.findById(idScheduling)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> schedulingService.findById(idScheduling));
        verify(schedulingRepository).findById(idScheduling);
    }

    @Test
    void updateCase1() {
        Integer idScheduling = 1;
        Scheduling oldScheduling = new Scheduling(idScheduling, "2023-01-10", "2023-01-01T10:00:00", "2023-01-01T12:00:00");
        Scheduling updateScheduling = new Scheduling(idScheduling, "2023-01-20", "2023-01-01T12:00:00", "2023-01-01T13:30:00");
        when(schedulingRepository.findById(idScheduling)).thenReturn(Optional.of(oldScheduling));
        when(schedulingRepository.save(oldScheduling)).thenReturn(oldScheduling);

        Scheduling result = schedulingService.update(idScheduling, updateScheduling);

        verify(schedulingRepository).findById(idScheduling);
        verify(schedulingRepository).save(oldScheduling);
        assertNotNull(result);
        assertEquals(oldScheduling.getStartOfService(), result.getStartOfService());
        assertEquals(oldScheduling.getEndOfService(), result.getEndOfService());
        assertEquals(oldScheduling.getDateScheduling(), result.getDateScheduling());
    }

    @Test
    void updateCase2() {
        Integer idScheduling = 1;
        when(schedulingRepository.findById(idScheduling)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> schedulingService.findById(idScheduling));
        verify(schedulingRepository).findById(idScheduling);
    }

}