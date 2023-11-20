package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.services.SchedulingService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/schedulings")
public class SchedulingController {

    private SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }
}
