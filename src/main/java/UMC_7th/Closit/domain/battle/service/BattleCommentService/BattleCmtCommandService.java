package UMC_7th.Closit.domain.battle.service.BattleCommentService;

import UMC_7th.Closit.domain.battle.dto.BattleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleComment;

public interface BattleCmtCommandService {
    BattleComment createBattleComment(Long battleId, BattleCommentRequestDTO.createBattleCommentRequestDTO request); // 배틀 댓글 생성
    void deleteBattleComment(Long battleId, Long battleCommentId); // 배틀 댓글 삭제
}