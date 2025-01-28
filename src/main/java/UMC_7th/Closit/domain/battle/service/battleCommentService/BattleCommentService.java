package UMC_7th.Closit.domain.battle.service.battleCommentService;

import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleComment;

public interface BattleCommentService {
    BattleComment createBattleComment(Long battleId, BattleCommentRequestDTO.createBattleCommentRequestDTO request); // 배틀 댓글 생성
}