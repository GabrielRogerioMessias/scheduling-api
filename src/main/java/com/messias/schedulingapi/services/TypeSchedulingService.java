package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.domain.TypeScheduling;
import com.messias.schedulingapi.repositories.EmployerRepository;
import com.messias.schedulingapi.repositories.TypeSchedulingRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSchedulingService {
    private final TypeSchedulingRepository typeSchedulingRepository;

    public TypeSchedulingService(TypeSchedulingRepository typeSchedulingRepository) {
        this.typeSchedulingRepository = typeSchedulingRepository;
    }


    public void delete(Integer idTypeScheduling) {
        TypeScheduling employer = typeSchedulingRepository.findById(idTypeScheduling).orElseThrow(() -> new ResourceNotFoundException(TypeScheduling.class, idTypeScheduling));
        try {
            typeSchedulingRepository.delete(employer);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<TypeScheduling> findAll() {
        return typeSchedulingRepository.findAll();

    }

    public TypeScheduling findById(Integer idTypeScheduling) {
        return typeSchedulingRepository.findById(idTypeScheduling).orElseThrow(() -> new ResourceNotFoundException(TypeScheduling.class, idTypeScheduling));
    }

    public TypeScheduling insert(TypeScheduling newTypeScheduling) {
        return typeSchedulingRepository.save(newTypeScheduling);
    }


    public TypeScheduling update(Integer idTypeScheduling, TypeScheduling updateTypeScheduling) {
        TypeScheduling oldTypeScheduling = typeSchedulingRepository.findById(idTypeScheduling).orElseThrow(() -> new ResourceNotFoundException(TypeScheduling.class, idTypeScheduling));
        updateData(oldTypeScheduling, updateTypeScheduling);
        return typeSchedulingRepository.save(oldTypeScheduling);
    }

    public void updateData(TypeScheduling oldTypeScheduling, TypeScheduling updateTypeScheduling) {
        oldTypeScheduling.setDescription(updateTypeScheduling.getDescription());
    }


}
