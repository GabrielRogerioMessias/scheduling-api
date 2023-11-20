package com.messias.schedulingapi.services;

import com.messias.schedulingapi.repositories.SchedulingEmployerRepository;
import org.springframework.stereotype.Service;

@Service
public class SchedulingEmployerService {
    private final SchedulingEmployerRepository schedulingEmployerRepository;

    public SchedulingEmployerService(SchedulingEmployerRepository schedulingEmployerRepository) {
        this.schedulingEmployerRepository = schedulingEmployerRepository;
    }
}
