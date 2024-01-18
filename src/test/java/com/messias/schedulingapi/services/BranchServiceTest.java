package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.repositories.BranchRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    BranchService branchService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Branch> branchList = new ArrayList<>();
        Branch branch1 = new Branch(1, "Branch1", "City1");
        Branch branch2 = new Branch(1, "Branch1", "City2");
        Branch branch3 = new Branch(1, "Branch1", "City3");

        branchList.add(branch1);
        branchList.add(branch2);
        branchList.add(branch3);

        when(branchService.findAll()).thenReturn(branchList);
        List<Branch> branches = branchService.findAll();
        assertEquals(branchList, branches);

    }

    @Test
    void findById() {
        Branch branch1 = new Branch(1, "Branch1", "City1");
        when(branchRepository.findById(1)).thenReturn(Optional.of(branch1));
        Branch result = branchService.findById(1);
        verify(branchRepository).findById(1);
        assertEquals(branch1, result);
    }

    @Test
    void insert() {
        Branch newBranch = new Branch(1, "Branch1", "City1");
        when(branchRepository.save(newBranch)).thenReturn(newBranch);
        Branch result = branchService.insert(newBranch);

        assertNotNull(result);
        assertEquals(newBranch.getId(), result.getId());
        assertEquals(newBranch.getNameBranch(), result.getNameBranch());
        assertEquals(newBranch.getCity(), result.getCity());
        //verifica se o método foi chamado com o novo branch
        verify(branchRepository).save(newBranch);
    }
}