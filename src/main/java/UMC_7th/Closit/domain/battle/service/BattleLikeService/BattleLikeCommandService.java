package UMC_7th.Closit.domain.battle.service.BattleLikeService;

import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleLike;

public interface BattleLikeCommandService {
    BattleLike createBattleLike(Long battleId, BattleLikeRequestDTO.CreateBattleLikeDTO request); // 배틀 좋아요 생성
    void deleteBattleLike(Long battleId, Long battleLikeId); // 배틀 좋아요 삭제
}