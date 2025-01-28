package UMC_7th.Closit.domain.battle.converter;

import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.BattleComment;
import UMC_7th.Closit.domain.user.entity.User;

import java.time.LocalDateTime;

public class BattleCommentConverter {

    public static BattleComment toBattleComment (User user, Battle battle, BattleCommentRequestDTO.createBattleCommentRequestDTO request) { // 배틀 댓글 생성
        return BattleComment.builder()
                .user(user)
                .battle(battle)
                .content(request.getContent())
                .build();
    }

    public static BattleCommentResponseDTO.createBattleCommentResultDTO createBattleCommentResponseDTO (BattleComment battleComment) {
        return BattleCommentResponseDTO.createBattleCommentResultDTO.builder()
                .battleCommentId(battleComment.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}