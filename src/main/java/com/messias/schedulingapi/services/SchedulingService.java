package com.messias.schedulingapi.services;


import com.messias.schedulingapi.domain.*;
import com.messias.schedulingapi.repositories.SchedulingRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService {
    private final SchedulingRepository schedulingRepository;
    private final BranchService branchService;
    private final UserService userService;
    private final DoctorService doctorService;
    private final SchedulingInfoService schedulingInfoService;

    public SchedulingService(SchedulingRepository schedulingRepository, BranchService branchService, UserService userService, DoctorService doctorService, SchedulingInfoService schedulingInfoService) {
        this.schedulingRepository = schedulingRepository;
        this.branchService = branchService;
        this.userService = userService;
        this.doctorService = doctorService;
        this.schedulingInfoService = schedulingInfoService;
    }

    public void delete(Integer idScheduling) {
        Scheduling scheduling = schedulingRepository.findById(idScheduling).orElseThrow(() -> new ResourceNotFoundException(Scheduling.class, idScheduling));
        try {
            schedulingRepository.delete(scheduling);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public List<Scheduling> findAll() {
        return schedulingRepository.findAll();
    }

    public Scheduling findById(Integer idScheduling) {
        return schedulingRepository.findById(idScheduling).orElseThrow(() -> new ResourceNotFoundException(Scheduling.class, idScheduling));
    }

    public Scheduling insert(Scheduling newScheduling) {
        Doctor doctor = doctorService.findById(newScheduling.getDoctor().getId());
        Branch branch = branchService.findById(newScheduling.getBranch().getId());
        User user = userService.findById(newScheduling.getUser().getId());
        SchedulingInfo schedulingInfo = schedulingInfoService.findById(newScheduling.getSchedulingInfo().getId());

        schedulingInfo.getSchedulingList().add(newScheduling);
        branch.getSchedulingList().add(newScheduling);
        doctor.getSchedulingList().add(newScheduling);
        user.getSchedulingList().add(newScheduling);

        newScheduling.setSchedulingInfo(schedulingInfo);
        newScheduling.setBranch(branch);
        newScheduling.setDoctor(doctor);
        newScheduling.setUser(user);
        return schedulingRepository.save(newScheduling);
    }

    public Scheduling update(Integer idScheduling, Scheduling updateScheduling) {
        Scheduling oldScheduling = schedulingRepository.findById(idScheduling).orElseThrow(() -> new ResourceNotFoundException(Scheduling.class, idScheduling));
        updateData(oldScheduling, updateScheduling);
        return schedulingRepository.save(oldScheduling);
    }


    public void updateData(Scheduling oldScheduling, Scheduling updateScheduling) {
        // Removing old relationships from the user, branch, and doctor entities.
        oldScheduling.getDoctor().getSchedulingList().remove(oldScheduling);
        oldScheduling.getUser().getSchedulingList().remove(oldScheduling);
        oldScheduling.getBranch().getSchedulingList().remove(oldScheduling);
        oldScheduling.getSchedulingInfo().getSchedulingList().remove(oldScheduling);

        //Updating entity data
        oldScheduling.setUser(updateScheduling.getUser());
        oldScheduling.setDoctor(updateScheduling.getDoctor());
        oldScheduling.setBranch(updateScheduling.getBranch());
        oldScheduling.setDateScheduling(updateScheduling.getDateScheduling());
        oldScheduling.setEndOfService(updateScheduling.getEndOfService());
        oldScheduling.setStartOfService(updateScheduling.getStartOfService());
        oldScheduling.setSchedulingInfo(updateScheduling.getSchedulingInfo());

        //Adding the updated scheduling entity to the lists in the user, branch, and doctor entities.
        updateScheduling.getUser().getSchedulingList().add(oldScheduling);
        updateScheduling.getBranch().getSchedulingList().add(oldScheduling);
        updateScheduling.getDoctor().getSchedulingList().add(oldScheduling);
        updateScheduling.getSchedulingInfo().getSchedulingList().add(oldScheduling);

    }

}
