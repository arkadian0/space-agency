package com.example.eversis.service.mission.port;

import com.example.eversis.controller.transfer.MissionDto;

public interface MissionServicePort {
    String save(MissionDto missionDto);

    void remove(String missionId);

    void update(MissionDto mission);

}
