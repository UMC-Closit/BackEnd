package UMC_7th.Closit.domain.battle.service.BattleCmtService;

import UMC_7th.Closit.domain.battle.dto.BattleCmtDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleComment;

public interface BattleCmtCommandService {
    BattleComment createBattleComment(Long battleId, BattleCommentRequestDTO.createBattleCommentRequestDTO request); // 배틀 댓글 생성
    void deleteBattleComment(Long battleId, Long battleCommentId); // 배틀 댓글 삭제
}