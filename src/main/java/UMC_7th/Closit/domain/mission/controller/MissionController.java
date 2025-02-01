package UMC_7th.Closit.domain.mission.controller;

import UMC_7th.Closit.domain.mission.converter.MissionConverter;
import UMC_7th.Closit.domain.mission.dto.MissionRequestDTO;
import UMC_7th.Closit.domain.mission.dto.MissionResponseDTO;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.mission.service.MissionCommandService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @Operation(summary = "미션 생성", description = "새로운 미션을 생성합니다.")
    @PostMapping
    public ApiResponse<MissionResponseDTO.CreateMissionResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.CreateMissionDTO request) {
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionResultDTO(mission));
    }

    @Operation(summary = "미션 완료", description = "특정 미션을 완료로 표시합니다.")
    @PutMapping("/{mission_id}/complete")
    public ApiResponse<MissionResponseDTO.UpdateMissionResultDTO> completeMission(@PathVariable Long mission_id) {
        Mission mission = missionCommandService.completeMission(mission_id);
        return ApiResponse.onSuccess(MissionConverter.toUpdateMissionResultDTO(mission));
    }
}