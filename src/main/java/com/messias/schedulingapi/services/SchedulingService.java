package com.messias.schedulingapi.services;


import com.messias.schedulingapi.repositories.SchedulingRepository;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {
    private final SchedulingRepository schedulingRepository;

    public SchedulingService(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }
}
