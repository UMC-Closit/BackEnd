package UMC_7th.Closit.domain.battle.repository;

import UMC_7th.Closit.domain.battle.entity.Battle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleRepository extends JpaRepository<Battle,Long> {
}
