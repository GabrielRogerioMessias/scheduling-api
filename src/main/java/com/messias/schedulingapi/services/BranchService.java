package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.repositories.BranchRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
    //  insert,

    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    public Branch findById(Integer idBranch) {
        return branchRepository.findById(idBranch).orElseThrow(() -> new ResourceNotFoundException(Branch.class, idBranch));
    }

    public void delete(Integer idBranch) {
        //[exception id not found]
        Branch branch = branchRepository.findById(idBranch).orElseThrow(() -> new ResourceNotFoundException(Branch.class, idBranch));
        try {
            branchRepository.delete(branch);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Branch insert(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch update(Integer idBranch, Branch updateBranch) {
        Branch branchOld = branchRepository.findById(idBranch).orElseThrow(() -> new ResourceNotFoundException(Branch.class, idBranch));
        this.updateDate(branchOld, updateBranch);
        return branchRepository.save(branchOld);
    }

    public void updateDate(Branch oldBranch, Branch updateBranch) {
        oldBranch.setNameBranch(updateBranch.getNameBranch());
        oldBranch.setCity(updateBranch.getCity());
    }

}
