package com.example.eversis.service.mission;

import com.example.eversis.controller.transfer.MissionDto;
import com.example.eversis.dao.MissionRepository;
import com.example.eversis.exceptions.MissionNotFoundException;
import com.example.eversis.model.Mission;
import com.example.eversis.service.mission.port.MissionServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
class MissionServiceImpl implements MissionServicePort {

    private final MissionRepository missionRepository;;


    public String save(MissionDto missionDto) {
        missionRepository.save(Mission.of(missionDto));
        return missionDto.getName();
    }

    public void remove(String missionId) {
        missionRepository.deleteById(missionId);
    }


    public void update(MissionDto missionDto) {
        Optional<Mission> mission = missionRepository.findById(missionDto.getName());

        if (mission.isPresent()) {
            missionRepository.save(Mission.of(missionDto));
        } else
            throw new MissionNotFoundException(String.format("Mission with id : %s not found", missionDto.getName()));
    }


}
