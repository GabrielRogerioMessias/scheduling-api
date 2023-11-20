package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.services.EmployerService;
import com.messias.schedulingapi.services.SchedulingEmployerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scheduling-employers")
public class SchedulingEmployerController {

    private SchedulingEmployerService schedulingEmployerService;

    public SchedulingEmployerController(SchedulingEmployerService schedulingEmployerService) {
        this.schedulingEmployerService = schedulingEmployerService;
    }
}
