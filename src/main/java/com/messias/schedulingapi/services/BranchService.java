package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.repositories.BranchRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import jakarta.servlet.http.PushBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Branch branch = branchRepository.findById(idBranch).orElseThrow(() -> new ResourceNotFoundException(idBranch));
        return branch;
    }

    public void delete(Integer idBranch) {
        //[exception id not found]
        Branch branch = branchRepository.findById(idBranch).orElseThrow(() -> new ResourceNotFoundException(idBranch));
        branchRepository.delete(branch);
    }

    public Branch insert(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch update(Integer idBranch, Branch newBranch) {
        Optional<Branch> branchOptional = branchRepository.findById(idBranch);
        Branch branchOld = branchOptional.get();
        this.updateDate(branchOld, newBranch);
        return branchRepository.save(branchOld);
    }

    public void updateDate(Branch oldBranch, Branch newBranch) {
        oldBranch.setNameBranch(newBranch.getNameBranch());
        oldBranch.setCity(newBranch.getCity());
    }

}
