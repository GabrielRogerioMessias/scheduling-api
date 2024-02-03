package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.repositories.EmployerRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployerServiceTest {
    @InjectMocks
    private EmployerService employerService;
    @Mock
    private EmployerRepository employerRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteCase1() {
        Integer idEmployer = 1;
        Employer employer = new Employer(idEmployer, "Employer Test");
        when(employerRepository.findById(idEmployer)).thenReturn(Optional.of(employer));
        doNothing().when(employerRepository).delete(employer);
        assertDoesNotThrow(() -> employerService.delete(idEmployer));
        verify(employerRepository).delete(employer);
        verify(employerRepository).findById(idEmployer);
    }

    @Test
    void deleteCase2() {
        Integer idEmployer = 1;
        Employer employer = new Employer(idEmployer, "Employer Test");
        when(employerRepository.findById(idEmployer)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employerService.delete(idEmployer));

        verify(employerRepository).findById(idEmployer);
        verify(employerRepository, never()).delete(any());

    }
}