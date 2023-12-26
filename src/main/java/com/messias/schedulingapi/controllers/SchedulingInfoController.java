package com.messias.schedulingapi.controllers;


import com.messias.schedulingapi.domain.SchedulingInfo;
import com.messias.schedulingapi.services.SchedulingInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/scheduling_info")
public class SchedulingInfoController {

    private final SchedulingInfoService schedulingInfoService;

    public SchedulingInfoController(SchedulingInfoService schedulingInfoService) {
        this.schedulingInfoService = schedulingInfoService;
    }

    @GetMapping
    public ResponseEntity<List<SchedulingInfo>> findAll() {
        List<SchedulingInfo> schedulingInfoList = schedulingInfoService.findAll();
        return ResponseEntity.ok().body(schedulingInfoList);
    }

    @DeleteMapping(value = "{idSchedulingInfo}")
    public ResponseEntity<Void> delete(@PathVariable Integer idSchedulingInfo) {
        schedulingInfoService.delete(idSchedulingInfo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{idSchedulingInfo}")
    public ResponseEntity<SchedulingInfo> findById(@PathVariable Integer idSchedulingInfo) {
        SchedulingInfo schedulingInfo = schedulingInfoService.findById(idSchedulingInfo);
        return ResponseEntity.ok().body(schedulingInfo);
    }

    @PostMapping
    public ResponseEntity<SchedulingInfo> insert(@RequestBody SchedulingInfo newSchedulingInfo) {
        SchedulingInfo schedulingInfo = schedulingInfoService.insert(newSchedulingInfo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(schedulingInfo.getId()).toUri();
        return ResponseEntity.created(uri).body(schedulingInfo);
    }

    @PutMapping("{idSchedulingInfo}")
    public ResponseEntity<SchedulingInfo> update(@PathVariable Integer idSchedulingInfo, @RequestBody SchedulingInfo updateSchedulingInfo) {
        schedulingInfoService.update(idSchedulingInfo, updateSchedulingInfo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
