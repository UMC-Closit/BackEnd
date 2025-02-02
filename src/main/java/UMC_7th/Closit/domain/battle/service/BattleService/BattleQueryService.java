package UMC_7th.Closit.domain.battle.service.BattleService;

import UMC_7th.Closit.domain.battle.entity.Battle;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface BattleQueryService {

    Optional<Battle> findBattle(Long battleId); // 배틀 게시글 목록 조회
    Slice<Battle> getBattleList (Integer page);

    Optional<Battle> findChallengeBattle(Long battleId); // 배틀 챌린지 게시글 목록 조회
    Slice<Battle> getChallengeBattleList (Integer page);
}