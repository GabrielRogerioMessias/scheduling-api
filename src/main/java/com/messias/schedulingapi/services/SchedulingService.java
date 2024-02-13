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
        // Removing old relationships from the user, branch, doctor, and schedulingInfo entities.
        Doctor oldDoctor = oldScheduling.getDoctor();
        if (oldDoctor != null) {
            oldDoctor.getSchedulingList().remove(oldScheduling);
        }

        User oldUser = oldScheduling.getUser();
        if (oldUser != null) {
            oldUser.getSchedulingList().remove(oldScheduling);
        }

        Branch oldBranch = oldScheduling.getBranch();
        if (oldBranch != null) {
            oldBranch.getSchedulingList().remove(oldScheduling);
        }

        SchedulingInfo oldSchedulingInfo = oldScheduling.getSchedulingInfo();
        if (oldSchedulingInfo != null) {
            oldSchedulingInfo.getSchedulingList().remove(oldScheduling);
        }

        // Updating entity data
        oldScheduling.setUser(updateScheduling.getUser());
        oldScheduling.setDoctor(updateScheduling.getDoctor());
        oldScheduling.setBranch(updateScheduling.getBranch());
        oldScheduling.setDateScheduling(updateScheduling.getDateScheduling());
        oldScheduling.setEndOfService(updateScheduling.getEndOfService());
        oldScheduling.setStartOfService(updateScheduling.getStartOfService());
        oldScheduling.setSchedulingInfo(updateScheduling.getSchedulingInfo());

        // Adding the updated scheduling entity to the lists in the user, branch, doctor, and schedulingInfo entities.
        User newUser = updateScheduling.getUser();
        if (newUser != null) {
            newUser.getSchedulingList().add(oldScheduling);
        }

        Branch newBranch = updateScheduling.getBranch();
        if (newBranch != null) {
            newBranch.getSchedulingList().add(oldScheduling);
        }

        Doctor newDoctor = updateScheduling.getDoctor();
        if (newDoctor != null) {
            newDoctor.getSchedulingList().add(oldScheduling);
        }

        SchedulingInfo newSchedulingInfo = updateScheduling.getSchedulingInfo();
        if (newSchedulingInfo != null) {
            newSchedulingInfo.getSchedulingList().add(oldScheduling);
        }
    }


}
