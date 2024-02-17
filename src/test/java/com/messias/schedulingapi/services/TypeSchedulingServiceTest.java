package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.TypeScheduling;
import com.messias.schedulingapi.repositories.TypeSchedulingRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @DisplayName("Test the findAll method when there are scheduling types in the list")
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

    @Test
    @DisplayName("Test findById when there is TypeScheduling with the given id")
    void findByIdCase1() {
        Integer idTyperScheduling = 1;
        TypeScheduling typeScheduling = new TypeScheduling();
        when(typeSchedulingRepository.findById(idTyperScheduling)).thenReturn(Optional.of(typeScheduling));
        TypeScheduling result = typeSchedulingService.findById(idTyperScheduling);
        assertAll(
                () -> verify(typeSchedulingRepository, times(1)).findById(idTyperScheduling),
                () -> assertEquals(typeScheduling, result)
        );
    }

    @Test
    @DisplayName("Test findById when there is no TypeScheduling with the given id")
    void findByIdCase2() {
        Integer idTypeSchedulign = 1;
        when(typeSchedulingRepository.findById(idTypeSchedulign)).thenReturn(Optional.empty());
        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> typeSchedulingService.findById(idTypeSchedulign)),
                () -> verify(typeSchedulingRepository, times(1)).findById(idTypeSchedulign)
        );
    }

}