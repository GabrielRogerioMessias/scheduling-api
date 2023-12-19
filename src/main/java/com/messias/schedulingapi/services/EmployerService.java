package com.messias.schedulingapi.services;

import com.messias.schedulingapi.controllers.EmployerController;
import com.messias.schedulingapi.domain.Doctor;
import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.repositories.DoctorRepository;
import com.messias.schedulingapi.repositories.EmployerRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {
    private EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public void delete(Integer idEmployer) {
        Employer employer = employerRepository.findById(idEmployer).orElseThrow(() -> new ResourceNotFoundException(idEmployer));
        employerRepository.delete(employer);
    }

    public List<Employer> findAll() {
        return employerRepository.findAll();

    }

    public Employer findById(Integer idEmployer) {
        return employerRepository.findById(idEmployer).orElseThrow(() -> new ResourceNotFoundException(idEmployer));
    }

    public Employer insert(Employer newEmployer) {
        return employerRepository.save(newEmployer);
    }


    public Employer update(Integer idEmployer, Employer updateEmployer) {
        Employer oldEmployer = employerRepository.findById(idEmployer).orElseThrow(() -> new ResourceNotFoundException(idEmployer));
        updateData(oldEmployer, updateEmployer);
        return employerRepository.save(oldEmployer);
    }

    public void updateData(Employer odlEmployer, Employer updateEmployer) {
        odlEmployer.setName(updateEmployer.getName());
    }


}


