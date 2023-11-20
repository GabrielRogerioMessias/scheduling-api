package com.messias.schedulingapi.services;

import com.messias.schedulingapi.repositories.TypeSchedulingRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeSchedulingService {
    private final TypeSchedulingRepository typeSchedulingRepository;

    public TypeSchedulingService(TypeSchedulingRepository typeSchedulingRepository) {
        this.typeSchedulingRepository = typeSchedulingRepository;
    }
}
