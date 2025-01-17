package UMC_7th.Closit.domain.battle.converter;

import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.post.entity.Post;

import java.time.LocalDateTime;

public class BattleConverter {

    public static Battle toBattle (Post post, BattleRequestDTO.CreateBattleDTO request) { // 배틀 생성
        return Battle.builder()
                .id(post.getId())
                .title(request.getTitle())
                .deadline(request.getDeadline())
                .build();
    }
    public static BattleResponseDTO.CreateBattleResultDTO createBattleResultDTO(Battle battle) {
        return BattleResponseDTO.CreateBattleResultDTO.builder()
                .userId(battle.getPost1().getUser().getId())
                .battleId(battle.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Battle toChallengeBattle (Post post, BattleRequestDTO.ChallengeBattleDTO request) { // 배틀 신청
        return Battle.builder()
                .id(post.getId())
                .build();
    }

    public static BattleResponseDTO.ChallengeBattleResultDTO challengeBattleResultDTO(Battle battle) {
        return BattleResponseDTO.ChallengeBattleResultDTO.builder()
                .firstPostId(battle.getPost1().getId())
                .secondPostId(battle.getPost2().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
