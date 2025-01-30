package UMC_7th.Closit.domain.mission.repository;

import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Slice<Mission> findAllByUser(User user, Pageable pageable);
}
