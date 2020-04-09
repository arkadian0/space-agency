package com.example.eversis.service.mission;

import com.example.eversis.dao.MissionRepository;
import com.example.eversis.exceptions.MissionNotFoundException;
import com.example.eversis.controller.transfer.MissionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MissionServiceImplUnitTest {


    @Mock
    private MissionRepository missionRepository;

    @InjectMocks
    private MissionServiceImpl sut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void throw_exception_when_mission_not_found() {
        //given
        final String missionId = "fake";
        MissionDto missionDto = MissionDto.builder().name(missionId).build();

        when(missionRepository.findById(any())).thenReturn(Optional.empty());

        //when
        Throwable thrown = catchThrowable(() -> sut.update(missionDto));

        // then
        assertThat(thrown).isInstanceOf(MissionNotFoundException.class)
                .hasMessageContaining(String.format("Mission with id : %s not found", missionId));

    }

}