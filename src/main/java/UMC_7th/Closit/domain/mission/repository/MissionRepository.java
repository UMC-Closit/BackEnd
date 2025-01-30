package UMC_7th.Closit.domain.mission.repository;

import UMC_7th.Closit.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
