package com.example.eversis.controller;

import com.example.eversis.controller.transfer.MissionDto;
import com.example.eversis.service.mission.port.MissionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mission")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionServicePort missionServicePort;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addMission(@RequestBody MissionDto missionDto) {
        String missionId = missionServicePort.save(missionDto);
        return new ResponseEntity<>(missionId, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<Object> updateProduct(@RequestBody MissionDto missionDto) {
        missionServicePort.update(missionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Object> deleteMission(@PathVariable(value = "id") String missionId) {
        missionServicePort.remove(missionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
