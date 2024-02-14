package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.repositories.BranchRepository;
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
    void update() {
        Integer idBranch = 1;
        Branch updateBranch = new Branch(idBranch, "Branch1", "City1");
        Branch oldBranch = new Branch(idBranch, "UpdateBranch", "NewCity");

        when(branchRepository.findById(idBranch)).thenReturn(java.util.Optional.of(oldBranch));
        when(branchRepository.save(oldBranch)).thenReturn(oldBranch);

        Branch result = branchService.update(idBranch, updateBranch);
        assertEquals(result.getNameBranch(), updateBranch.getNameBranch());
        assertEquals(result.getCity(), updateBranch.getCity());
        verify(branchRepository).save(oldBranch);
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

    @Test
    void delete() {
        Integer idBranch = 1;
        Branch deleteBranch = new Branch(idBranch, "Branch1", "City");
        when(branchRepository.findById(idBranch)).thenReturn(Optional.of(deleteBranch));
        doNothing().when(branchRepository).delete(deleteBranch);
        assertDoesNotThrow(() -> branchService.delete(idBranch));
        verify(branchRepository).findById(idBranch);

    }

}