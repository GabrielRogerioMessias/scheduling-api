package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.SchedulingInfo;
import com.messias.schedulingapi.repositories.SchedulingInfoRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
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

class SchedulingInfoServiceTest {
    @InjectMocks
    SchedulingInfoService schedulingInfoService;
    @Mock
    SchedulingInfoRepository schedulingInfoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteCase1() {
        Integer idSchedulingInfo = 1;
        SchedulingInfo schedulingInfo = new SchedulingInfo(1, true, "COMMON");
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.of(schedulingInfo));
        doNothing().when(schedulingInfoRepository).delete(schedulingInfo);
        schedulingInfoService.delete(idSchedulingInfo);
        verify(schedulingInfoRepository, times(1)).delete(schedulingInfo);
    }

    @Test
    void deleteCase2() {
        Integer idSchedulingInfo = 1;
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.empty());
        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> schedulingInfoService.findById(idSchedulingInfo)),
                () -> verify(schedulingInfoRepository).findById(idSchedulingInfo),
                () -> verify(schedulingInfoRepository, never()).delete(any())
        );
    }

    @Test
    void deleteCase3() {
        Integer idSchedulingInfo = 1;
        SchedulingInfo schedulingInfo = new SchedulingInfo(1, true, "COMMON");
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.of(schedulingInfo));
        doThrow(DatabaseException.class).when(schedulingInfoRepository).delete(schedulingInfo);
        assertThrows(DatabaseException.class, () -> schedulingInfoService.delete(idSchedulingInfo));
    }

    @Test
    void findAll() {
        List<SchedulingInfo> schedulingInfoList = Arrays.asList(
                new SchedulingInfo(1, true, "COMMON"),
                new SchedulingInfo(2, false, "HOLIDAY")
        );
        when(schedulingInfoRepository.findAll()).thenReturn(schedulingInfoList);
        List<SchedulingInfo> result = schedulingInfoService.findAll();
        assertAll(
                () -> verify(schedulingInfoRepository).findAll(),
                () -> assertEquals(schedulingInfoList, result),
                () -> assertEquals(schedulingInfoList.size(), result.size())
        );
    }

    @Test
    void findByIdCase1() {
        Integer idSchedulingInfo = 1;
        SchedulingInfo schedulingInfo = new SchedulingInfo(idSchedulingInfo, true, "COMMON");
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.of(schedulingInfo));
        SchedulingInfo result = schedulingInfoService.findById(idSchedulingInfo);
        assertAll(
                () -> verify(schedulingInfoRepository).findById(idSchedulingInfo),
                () -> assertNotNull(result),
                () -> assertEquals(schedulingInfo, result)
        );
    }

    @Test
    void findByIdCase2() {
        Integer idSchedulingInfo = 1;
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.empty());
        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> schedulingInfoService.findById(idSchedulingInfo)),
                () -> verify(schedulingInfoRepository).findById(idSchedulingInfo)
        );
    }

    @Test
    void insert() {
        Integer idSchedulingInfo = 1;
        SchedulingInfo schedulingInfo = new SchedulingInfo(idSchedulingInfo, true, "COMMON");
        when(schedulingInfoRepository.save(schedulingInfo)).thenReturn(schedulingInfo);
        SchedulingInfo result = schedulingInfoService.insert(schedulingInfo);
        assertAll(
                () -> verify(schedulingInfoRepository, times(1)).save(schedulingInfo),
                () -> assertNotNull(result),
                () -> assertEquals(schedulingInfo, result)
        );
    }

    @Test
    void updateCase1() {
        Integer idSchedulingInfo = 1;
        SchedulingInfo oldSchedulingInfo = new SchedulingInfo(idSchedulingInfo, true, "TEST");
        SchedulingInfo updateSchedulingInfo = new SchedulingInfo(idSchedulingInfo, false, "UPDATE");
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.of(oldSchedulingInfo));
        when(schedulingInfoRepository.save(oldSchedulingInfo)).thenReturn(oldSchedulingInfo);
        SchedulingInfo result = schedulingInfoService.update(idSchedulingInfo, updateSchedulingInfo);

        assertAll(
                () -> verify(schedulingInfoRepository, times(1)).findById(idSchedulingInfo),
                () -> verify(schedulingInfoRepository, times(1)).save(oldSchedulingInfo),
                () -> assertEquals(updateSchedulingInfo, result),
                () -> assertEquals(updateSchedulingInfo.getDescription(), result.getDescription()),
                () -> assertEquals(updateSchedulingInfo.isHasScheduling(), result.isHasScheduling())
        );
    }

    @Test
    void updateCase2() {
        Integer idSchedulingInfo = 1;
        when(schedulingInfoRepository.findById(idSchedulingInfo)).thenReturn(Optional.empty());
        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> schedulingInfoService.findById(idSchedulingInfo)),
                () -> verify(schedulingInfoRepository).findById(idSchedulingInfo),
                () -> verify(schedulingInfoRepository, never()).save(any())
        );
    }

    @Test
    void checkScheduling() {
        Integer idSchedulingInfo = 1;
        Boolean expectedVerify = true;
        when(schedulingInfoRepository.findByHasScheduling(idSchedulingInfo)).thenReturn(expectedVerify);
        Boolean result = schedulingInfoService.checkScheduling(idSchedulingInfo);
        assertAll(
                () -> verify(schedulingInfoRepository).findByHasScheduling(idSchedulingInfo),
                () -> assertEquals(expectedVerify, result)
        );
    }

}