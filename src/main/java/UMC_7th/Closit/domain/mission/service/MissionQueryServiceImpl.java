package UMC_7th.Closit.domain.mission.service;

import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.mission.repository.MissionRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.MissionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Mission findMission(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
    }
}
