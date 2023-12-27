package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.SchedulingInfo;
import com.messias.schedulingapi.repositories.SchedulingInfoRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingInfoService {
    private final SchedulingInfoRepository schedulingInfoRepository;

    public SchedulingInfoService(SchedulingInfoRepository branchRepository) {
        this.schedulingInfoRepository = branchRepository;
    }

    public List<SchedulingInfo> findAll() {
        return schedulingInfoRepository.findAll();
    }

    public SchedulingInfo findById(Integer idSchedulingInfo) {
        SchedulingInfo schedulingInfo = schedulingInfoRepository.findById(idSchedulingInfo).orElseThrow(() -> new ResourceNotFoundException(SchedulingInfo.class, idSchedulingInfo));
        return schedulingInfo;
    }

    public void delete(Integer idSchedulingInfo) {
        SchedulingInfo schedulingInfo = schedulingInfoRepository.findById(idSchedulingInfo).orElseThrow(() -> new ResourceNotFoundException(SchedulingInfo.class, idSchedulingInfo));
        try {
            schedulingInfoRepository.delete(schedulingInfo);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public SchedulingInfo insert(SchedulingInfo schedulingInfo) {
        return schedulingInfoRepository.save(schedulingInfo);
    }

    public SchedulingInfo update(Integer idSchedulingInfo, SchedulingInfo updateScheduling) {
        SchedulingInfo oldSchedulingInfo = schedulingInfoRepository.findById(idSchedulingInfo).orElseThrow(() -> new ResourceNotFoundException(SchedulingInfo.class, idSchedulingInfo));
        this.updateDate(oldSchedulingInfo, updateScheduling);
        return schedulingInfoRepository.save(oldSchedulingInfo);
    }

    public void updateDate(SchedulingInfo oldSchedulingInfo, SchedulingInfo updateSchedulingInfo) {
        oldSchedulingInfo.setDescription(updateSchedulingInfo.getDescription());
        oldSchedulingInfo.setHasScheduling(updateSchedulingInfo.isHasScheduling());
    }

    public Boolean checkScheduling(Integer idSchedulingInfo) {
        Boolean verify = schedulingInfoRepository.findByHasScheduling(idSchedulingInfo);
        return verify;
    }

}
