package com.messias.schedulingapi.services;

import com.messias.schedulingapi.repositories.BranchRepository;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }
}
