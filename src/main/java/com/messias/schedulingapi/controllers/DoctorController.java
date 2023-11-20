package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.services.BranchService;
import com.messias.schedulingapi.services.DoctorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
