package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.repositories.BranchRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    private BranchService branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        branchService = new BranchService(branchRepository);
    }

    @Test
    public void findAll() {
        List<Branch> branchList = new ArrayList<>();
        Branch branch1 = new Branch(1, "Branch1", "City1");
        Branch branch2 = new Branch(1, "Branch1", "City2");
        Branch branch3 = new Branch(1, "Branch1", "City3");

        branchList.add(branch1);
        branchList.add(branch2);
        branchList.add(branch3);

        when(branchRepository.findAll()).thenReturn(branchList);

        List<Branch> branches = branchService.findAll();

        assertEquals(branchList, branches);


    }

}