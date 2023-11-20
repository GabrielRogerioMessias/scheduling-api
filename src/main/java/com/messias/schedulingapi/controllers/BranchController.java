package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.services.BranchService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/branchs")
public class BranchController {

    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }
}
