package UMC_7th.Closit.domain.battle.converter;

import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import UMC_7th.Closit.domain.user.entity.User;

import java.time.LocalDateTime;

public class BattleLikeConverter {

    public static BattleLike toBattleLike (User user, Battle battle) { // 배틀 좋아요 생성
        return BattleLike.builder()
                .user(user)
                .battle(battle)
                .build();
    }

    public static BattleLikeResponseDTO.CreateBattleLikeResultDTO createBattleLikeResultDTO(BattleLike battleLike) {
        return BattleLikeResponseDTO.CreateBattleLikeResultDTO.builder()
                .battleLikeId(battleLike.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}