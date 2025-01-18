package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.entity.Battle;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface BattleQueryService {

    Optional<Battle> findBattle(Long battleId); // 배틀 게시판 조회
    Slice<Battle> getBattleList (Integer page);
}
