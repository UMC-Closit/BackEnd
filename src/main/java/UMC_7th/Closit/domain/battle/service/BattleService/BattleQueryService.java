package UMC_7th.Closit.domain.battle.service.BattleService;

import UMC_7th.Closit.domain.battle.entity.Battle;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface BattleQueryService {

    Slice<Battle> getBattleList (Long userId, Integer page); // 배틀 게시글 목록 조회
    Slice<Battle> getChallengeBattleList (Long userId, Integer page); // 배틀 챌린지 게시글 목록 조회
}