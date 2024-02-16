package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.TypeScheduling;
import com.messias.schedulingapi.repositories.TypeSchedulingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TypeSchedulingServiceTest {
    @InjectMocks
    TypeSchedulingService typeSchedulingService;
    @Mock
    TypeSchedulingRepository typeSchedulingRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<TypeScheduling> typeSchedulingList = Arrays.asList(
                new TypeScheduling(1, "ADM"),
                new TypeScheduling(2, "DM")
        );
        when(typeSchedulingRepository.findAll()).thenReturn(typeSchedulingList);

        List<TypeScheduling> result = typeSchedulingService.findAll();

        assertAll(
                () -> verify(typeSchedulingRepository).findAll(),
                () -> assertEquals(typeSchedulingList, result)
        );
    }
}