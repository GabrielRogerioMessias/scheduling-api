package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Doctor;
import com.messias.schedulingapi.repositories.DoctorRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void delete(Integer idDoctor) {
        // exception cascate
        Doctor doctor = doctorRepository.findById(idDoctor).orElseThrow(() -> new ResourceNotFoundException(idDoctor));
        doctorRepository.delete(doctor);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();

    }

    public Doctor findById(Integer idDoctor) {
        Doctor doctor = doctorRepository.findById(idDoctor).orElseThrow(() -> new ResourceNotFoundException(idDoctor));
        return doctor;
    }

    public Doctor insert(Doctor newDoctor) {
        return doctorRepository.save(newDoctor);
    }


    public Doctor update(Integer idDoctor, Doctor updateDoctor) {
        Doctor oldDoctor = doctorRepository.findById(idDoctor).orElseThrow(() -> new ResourceNotFoundException(idDoctor));
        updateData(oldDoctor, updateDoctor);
        return doctorRepository.save(oldDoctor);
    }

    public void updateData(Doctor oldDoctor, Doctor updateDoctor) {
        oldDoctor.setName(updateDoctor.getName());
        oldDoctor.setCRM(updateDoctor.getCRM());
    }

}
