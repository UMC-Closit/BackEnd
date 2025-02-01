package UMC_7th.Closit.domain.mission.service;

import UMC_7th.Closit.domain.mission.dto.MissionRequestDTO;
import UMC_7th.Closit.domain.mission.entity.Mission;

public interface MissionCommandService {

    Mission createMission(MissionRequestDTO.CreateMissionDTO request);

    Mission completeMission(Long missionId);

    void deleteMission(Long missionId);
}
