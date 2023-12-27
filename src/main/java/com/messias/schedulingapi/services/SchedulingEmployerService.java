package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.*;
import com.messias.schedulingapi.repositories.*;
import com.messias.schedulingapi.services.exceptionsServices.CannotScheduleException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;

@Service
public class SchedulingEmployerService {
    private final SchedulingEmployerRepository schedulingEmployerRepository;
    private final SchedulingService schedulingService;
    private final TypeSchedulingService typeSchedulingService;
    private final UserService userService;
    private final EmployerService employerService;
    private final SchedulingInfoService schedulingInfoService;

    public SchedulingEmployerService(SchedulingEmployerRepository schedulingEmployerRepository, SchedulingService schedulingService,
                                     TypeSchedulingService typeSchedulingService, UserService userService, EmployerService employerService, SchedulingInfoService schedulingInfoService) {
        this.schedulingEmployerRepository = schedulingEmployerRepository;
        this.schedulingService = schedulingService;
        this.typeSchedulingService = typeSchedulingService;
        this.userService = userService;
        this.employerService = employerService;
        this.schedulingInfoService = schedulingInfoService;
    }

    public void delete(Integer idSchedulingEmployer) {
        SchedulingEmployer schedulingEmployer = schedulingEmployerRepository.findById(idSchedulingEmployer).orElseThrow(() -> new ResourceNotFoundException(SchedulingEmployer.class, idSchedulingEmployer));
        schedulingEmployerRepository.delete(schedulingEmployer);
    }

    public List<SchedulingEmployer> findAll() {
        return schedulingEmployerRepository.findAll();
    }

    public SchedulingEmployer findById(Integer idSchedulingEmployer) {
        SchedulingEmployer schedulingEmployer = schedulingEmployerRepository.findById(idSchedulingEmployer).orElseThrow(() -> new ResourceNotFoundException(SchedulingEmployer.class, idSchedulingEmployer));
        return schedulingEmployer;
    }

    public SchedulingEmployer insert(SchedulingEmployer newSchedulingEmployer) {
        Scheduling scheduling = schedulingService.findById(newSchedulingEmployer.getScheduling().getId());
        Boolean verifyInfoScheduling = schedulingInfoService.checkScheduling(scheduling.getSchedulingInfo().getId());
        if (verifyInfoScheduling.equals(true)) {
            Employer employer = employerService.findById(newSchedulingEmployer.getEmployer().getId());

            User user = userService.findById(newSchedulingEmployer.getUser().getId());
            TypeScheduling typeScheduling = typeSchedulingService.findById(newSchedulingEmployer.getTypeScheduling().getIdTypeScheduling());

            newSchedulingEmployer.setEmployer(employer);
            newSchedulingEmployer.setScheduling(scheduling);
            newSchedulingEmployer.setUser(user);
            newSchedulingEmployer.setTypeScheduling(typeScheduling);

            addToCollectionIfNotPresent(employer.getSchedulingEmployerList(), newSchedulingEmployer);
            addToCollectionIfNotPresent(scheduling.getSchedulingEmployerList(), newSchedulingEmployer);
            addToCollectionIfNotPresent(user.getSchedulingEmployerList(), newSchedulingEmployer);
            addToCollectionIfNotPresent(typeScheduling.getSchedulingEmployers(), newSchedulingEmployer);
            return schedulingEmployerRepository.save(newSchedulingEmployer);
        } else {
            throw new CannotScheduleException();
        }

    }

    public SchedulingEmployer update(Integer idSchedulingEmployer, SchedulingEmployer updateScheduling) {
        SchedulingEmployer oldSchedulingEmployer = schedulingEmployerRepository.findById(idSchedulingEmployer).orElseThrow(() -> new ResourceNotFoundException(SchedulingEmployer.class, idSchedulingEmployer));
        updateData(oldSchedulingEmployer, updateScheduling);
        return schedulingEmployerRepository.save(oldSchedulingEmployer);

    }

    public void updateData(SchedulingEmployer oldSchedulingEmployer, SchedulingEmployer updateScheduling) {
        oldSchedulingEmployer.getScheduling().getSchedulingEmployerList().remove(oldSchedulingEmployer);
        oldSchedulingEmployer.getEmployer().getSchedulingEmployerList().remove(oldSchedulingEmployer);
        oldSchedulingEmployer.getTypeScheduling().getSchedulingEmployers().remove(oldSchedulingEmployer);
        oldSchedulingEmployer.getUser().getSchedulingEmployerList().remove(oldSchedulingEmployer);

        oldSchedulingEmployer.setScheduling(updateScheduling.getScheduling());
        oldSchedulingEmployer.setEmployer(updateScheduling.getEmployer());
        oldSchedulingEmployer.setTypeScheduling(updateScheduling.getTypeScheduling());
        oldSchedulingEmployer.setUser(updateScheduling.getUser());
        oldSchedulingEmployer.setServiceTime(updateScheduling.getServiceTime());
        oldSchedulingEmployer.setPresence(updateScheduling.isPresence());

        addToCollectionIfNotPresent(oldSchedulingEmployer.getEmployer().getSchedulingEmployerList(), oldSchedulingEmployer);
        addToCollectionIfNotPresent(oldSchedulingEmployer.getScheduling().getSchedulingEmployerList(), oldSchedulingEmployer);
        addToCollectionIfNotPresent(oldSchedulingEmployer.getTypeScheduling().getSchedulingEmployers(), oldSchedulingEmployer);
        addToCollectionIfNotPresent(oldSchedulingEmployer.getUser().getSchedulingEmployerList(), oldSchedulingEmployer);

    }


    private <T> void addToCollectionIfNotPresent(Collection<T> collection, T element) {
        if (!collection.contains(element)) {
            collection.add(element);
        }
    }


}


