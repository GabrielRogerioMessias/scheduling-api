package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.repositories.EmployerRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
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
        when(employerRepository.findById(idEmployer)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employerService.delete(idEmployer));
        verify(employerRepository).findById(idEmployer);
        verify(employerRepository, never()).delete(any());
    }

    @Test
    void findByIdCase1() {
        Integer idEmployer = 1;
        Employer employer = new Employer(idEmployer, "Employer Test");
        when(employerRepository.findById(idEmployer)).thenReturn(Optional.of(employer));

        Employer result = employerRepository.findById(idEmployer).get();

        assertEquals(employer, result);
        assertDoesNotThrow(() -> employerService.findById(1));
        assertNotNull(result);
        assertEquals(employer.getName(), result.getName());
    }

    @Test
    void findByIdCase2() {
        Integer idEmployer = 1;
        when(employerRepository.findById(idEmployer)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> employerService.findById(idEmployer));
        verify(employerRepository).findById(idEmployer);
    }

    @Test
    void findAll() {
        List<Employer> list = new ArrayList<>();
        Employer employer1 = new Employer(1, "Employer Test");
        Employer employer2 = new Employer(2, "Employer Test");
        Employer employer3 = new Employer(3, "Employer Test");
        list.add(employer3);
        list.add(employer2);
        list.add(employer1);
        when(employerRepository.findAll()).thenReturn(list);

        List<Employer> result = employerService.findAll();

        verify(employerRepository).findAll();
        assertEquals(list, result);
        assertEquals(list.size(), result.size());
        assertDoesNotThrow(() -> employerService.findAll());
    }

    @Test
    void insertCase1() {
        Integer idEmployer = 1;
        Employer employer = new Employer(idEmployer, "Employer Test");
        when(employerRepository.save(employer)).thenReturn(employer);
        Employer result = employerService.insert(employer);
        verify(employerRepository).save(employer);
        assertNotNull(result);
        assertEquals(employer, result);
        assertNotNull(result);

    }

    @Test
    void insertCase2() {
        Employer employer = new Employer();
        when(employerRepository.save(employer)).thenReturn(null);
        doNothing().when(employerService.insert(employer));
        verify(employerRepository.save(employer));
    }

    @Test
    void updateCase1() {
        Integer idEmployer = 1;
        Employer employerOld = new Employer(idEmployer, "Employer Test");
        Employer employerUpdate = new Employer(idEmployer, "Employer Update");
        when(employerRepository.findById(idEmployer)).thenReturn(Optional.of(employerOld));
        when(employerRepository.save(employerOld)).thenReturn(employerOld);

        Employer resul = employerService.update(idEmployer, employerUpdate);
        verify(employerRepository).save(employerOld);
        verify(employerRepository).findById(idEmployer);
        assertEquals(resul.getName(), employerUpdate.getName());
        assertEquals(resul.getId(), employerUpdate.getId());
    }

}