package UMC_7th.Closit.domain.battle.repository;

import UMC_7th.Closit.domain.battle.entity.Battle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BattleRepository extends JpaRepository<Battle,Long> {

    Optional<Battle> findById(Long battleId);

    Slice<Battle> findByPost2IsNotNull(Pageable pageable); // 배틀 게시글 목록 조회
    Slice<Battle> findByPost2IsNull(Pageable pageable); // 배틀 챌린지 게시글 목록 조회
}