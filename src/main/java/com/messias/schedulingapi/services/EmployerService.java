package com.messias.schedulingapi.services;

import com.messias.schedulingapi.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {
    private final EmployerService employerService;

    public EmployerService(EmployerService employerService) {
        this.employerService = employerService;
    }
}
