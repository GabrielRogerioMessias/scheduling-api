package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Scheduling;
import com.messias.schedulingapi.domain.SchedulingEmployer;
import com.messias.schedulingapi.repositories.SchedulingEmployerRepository;
import com.messias.schedulingapi.repositories.SchedulingRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SchedulingEmployerServiceTest {
    @InjectMocks
    SchedulingEmployerService schedulingEmployerService;
    @Mock
    SchedulingEmployerRepository schedulingEmployerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When findAll method return a list")
    void findAll() {
        List<SchedulingEmployer> schedulingEmployerList = Arrays.asList();
        when(schedulingEmployerRepository.findAll()).thenReturn(schedulingEmployerList);
        List<SchedulingEmployer> result = schedulingEmployerService.findAll();
        verify(schedulingEmployerRepository).findAll();
        assertEquals(schedulingEmployerList, result);
    }

    @Test
    @DisplayName("When findById method return a SchedulingEmployer")
    void findByIdCase1() {
        Integer idSchedulingEmployer = 1;
        SchedulingEmployer schedulingEmployer = new SchedulingEmployer();
        schedulingEmployer.setId(idSchedulingEmployer);
        when(schedulingEmployerRepository.findById(idSchedulingEmployer)).thenReturn(Optional.of(schedulingEmployer));
        SchedulingEmployer result = schedulingEmployerService.findById(idSchedulingEmployer);
        assertEquals(schedulingEmployer.getId(), result.getId());
        verify(schedulingEmployerRepository).findById(idSchedulingEmployer);
    }
    @Test
    @DisplayName("When findById method return a Exception")
    void findByIdCase2(){
        Integer idSchedulingEmployer = 1;
        when(schedulingEmployerRepository.findById(idSchedulingEmployer)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> schedulingEmployerService.findById(idSchedulingEmployer));
        verify(schedulingEmployerRepository).findById(idSchedulingEmployer);
    }

}