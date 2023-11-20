package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.services.SchedulingService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/type-schedulings")
public class TypeSchedulingController {

    private SchedulingService schedulingService;

    public TypeSchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }
}
