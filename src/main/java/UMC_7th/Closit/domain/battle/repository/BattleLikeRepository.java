package UMC_7th.Closit.domain.battle.repository;

import UMC_7th.Closit.domain.battle.entity.BattleLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleLikeRepository extends JpaRepository<BattleLike, Long> {

   boolean existsBattleLikeByBattleIdAndUserId(Long battleId, Long userId); // 배틀 좋아요 생성
   Slice<BattleLike> findAllByBattleId(Long battleId, Pageable pageable);
}