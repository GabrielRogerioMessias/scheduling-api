package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.services.EmployerService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employers")
public class EmployerController {

    private EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }
}
