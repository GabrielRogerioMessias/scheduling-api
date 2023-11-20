package com.messias.schedulingapi.services;


import org.springframework.stereotype.Service;

@Service
public class SchedulingService {
    private final SchedulingService schedulingService;

    public SchedulingService(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }
}
