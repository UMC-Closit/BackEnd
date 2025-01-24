package UMC_7th.Closit.domain.battle.service.BattleLikeService;

import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleLike;

public interface BattleLikeService {
    BattleLike createBattleLike(Long battleId, BattleLikeRequestDTO.CreateBattleLikeDTO request); // 배틀 좋아요 생성
}