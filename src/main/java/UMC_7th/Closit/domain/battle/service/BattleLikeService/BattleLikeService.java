package UMC_7th.Closit.domain.battle.service.BattleLikeService;

import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface BattleLikeService {
    BattleLike createBattleLike(Long battleId, BattleLikeRequestDTO.CreateBattleLikeDTO request); // 배틀 좋아요 생성
    Slice<BattleLike> getBattleLikeList(Long battleId, Integer page); // 배틀 좋아요 조회
    void deleteBattleLike(Long battleId, Long battleLikeId); // 배틀 좋아요 삭제
}