package UMC_7th.Closit.domain.mission.service;

import UMC_7th.Closit.domain.mission.converter.MissionConverter;
import UMC_7th.Closit.domain.mission.dto.MissionRequestDTO;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.mission.entity.MissionStatus;
import UMC_7th.Closit.domain.mission.repository.MissionRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.MissionHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Mission createMission(MissionRequestDTO.CreateMissionDTO request) {
        User user = userRepository.findById(request.getUser())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Mission newMission = MissionConverter.toMission(request, user);

        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    public Mission completeMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MISSION_NOT_FOUND));

        mission.updateMission(MissionStatus.valueOf("COMPLETE"));

        return mission;
    }

    @Override
    @Transactional
    public void deleteMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        missionRepository.delete(mission);
    }
}
