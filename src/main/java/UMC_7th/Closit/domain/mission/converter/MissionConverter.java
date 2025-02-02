package UMC_7th.Closit.domain.mission.converter;

import UMC_7th.Closit.domain.mission.dto.MissionRequestDTO;
import UMC_7th.Closit.domain.mission.dto.MissionResponseDTO;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.mission.entity.MissionStatus;
import UMC_7th.Closit.domain.user.entity.User;

import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .userId(mission.getUser().getId())
                .status(mission.getStatus().name())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.UpdateMissionResultDTO toUpdateMissionResultDTO(Mission mission) {
        return MissionResponseDTO.UpdateMissionResultDTO.builder()
                .missionId(mission.getId())
                .userId(mission.getUser().getId())
                .status(mission.getStatus().name())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .userId(mission.getUser().getId())
                .status(mission.getStatus().name())
                .createdAt(mission.getCreatedAt())
                .updatedAt(mission.getUpdatedAt())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.CreateMissionDTO request, User user) {
        return Mission.builder()
                .user(user)
                .status(MissionStatus.valueOf(request.getStatus().toUpperCase()))
                .build();
    }
}
