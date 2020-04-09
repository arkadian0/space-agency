package com.example.eversis.service.mission;

import com.example.eversis.dao.MissionRepository;
import com.example.eversis.enums.ImageryType;
import com.example.eversis.model.Mission;
import com.example.eversis.controller.transfer.MissionDto;
import com.example.eversis.service.mission.port.MissionServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MissionServiceImplIntegrationTest {

    private LocalDateTime fakeLocalDateTime = LocalDateTime.of(2019,2,2,1,1);
    Mission mission = new Mission("added1", ImageryType.PANCHROMATIC, fakeLocalDateTime, fakeLocalDateTime.plusDays(10));
    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private MissionServicePort sut;

    @BeforeEach
    void setUp(){
        missionRepository.save(mission);
    }

    @Test
    void check_correct_of_save_mission() {
        //given
        MissionDto missionDto = MissionDto.builder().finishDate(fakeLocalDateTime)
                .imageryType(ImageryType.MULTISPECTRAL)
                .startDate(fakeLocalDateTime.plusDays(1))
                .name("examplename").build();

        //when
        String missionId = sut.save(missionDto);

        //then
        Optional<Mission> mission = missionRepository.findById("examplename");
        assertTrue(mission.isPresent());
        assertEquals(mission.get().getName(), missionId);
        assertEquals(mission.get().getName(), missionDto.getName());
        assertEquals(mission.get().getFinishDate(), missionDto.getFinishDate());
        assertEquals(mission.get().getStartDate(), missionDto.getStartDate());
        assertEquals(mission.get().getImageryType(), missionDto.getImageryType());

    }

    @Test
    void check_correct_of_update_mission() {
        //given
        MissionDto missionDto = MissionDto.builder().finishDate(fakeLocalDateTime.minusDays(100))
                .imageryType(ImageryType.HYPERSPECTRAL)
                .startDate(fakeLocalDateTime.minusDays(50))
                .name("added1").build();

        //when
        sut.save(missionDto);

        //then
        Optional<Mission> mission = missionRepository.findById("added1");
        assertTrue(mission.isPresent());
        assertEquals(mission.get().getName(), missionDto.getName());
        assertEquals(mission.get().getFinishDate(), missionDto.getFinishDate());
        assertEquals(mission.get().getStartDate(), missionDto.getStartDate());
        assertEquals(mission.get().getImageryType(), missionDto.getImageryType());

    }

    @Test
    void check_correct_of_delete_mission()
    {
        //given
        final String missionId = "added1";

        //when
        sut.remove(missionId);

        //then
        Optional<Mission> mission = missionRepository.findById(missionId);
        assertFalse(mission.isPresent());
    }


}