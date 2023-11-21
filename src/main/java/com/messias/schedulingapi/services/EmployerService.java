package com.messias.schedulingapi.services;

import com.messias.schedulingapi.controllers.EmployerController;
import com.messias.schedulingapi.repositories.DoctorRepository;
import com.messias.schedulingapi.repositories.EmployerRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {
    private EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }
}
