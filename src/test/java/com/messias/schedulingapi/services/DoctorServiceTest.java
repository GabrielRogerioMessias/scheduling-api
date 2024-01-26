package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.domain.Doctor;
import com.messias.schedulingapi.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insert() {
        Integer idDoctor = 1;
        Doctor newDoctor = new Doctor(idDoctor, "Doctor Test", "0150156");
        when(doctorRepository.save(newDoctor)).thenReturn(newDoctor);
        Doctor result = doctorService.insert(newDoctor);
        assertNotNull(result);
        assertEquals(newDoctor, result);
        assertEquals(newDoctor.getId(), result.getId());
        assertEquals(newDoctor.getName(), result.getName());
        assertEquals(newDoctor.getCRM(), result.getCRM());
        verify(doctorRepository).save(newDoctor);
    }

    @Test
    void findAll() {
        List<Doctor> listDoctor = new ArrayList<>();
        Doctor doctor1 = new Doctor(1, "Doctor Test", "0150156");
        Doctor doctor2 = new Doctor(2, "Doctor Test", "0150156");
        Doctor doctor3 = new Doctor(3, "Doctor Test", "0150156");

        listDoctor.add(doctor1);
        listDoctor.add(doctor2);
        listDoctor.add(doctor3);

        when(doctorRepository.findAll()).thenReturn(listDoctor);

        List<Doctor> result = doctorService.findAll();

        assertNotNull(result);
        assertEquals(listDoctor, result);
        assertEquals(listDoctor.size(), result.size());
        verify(doctorRepository).findAll();

    }

}