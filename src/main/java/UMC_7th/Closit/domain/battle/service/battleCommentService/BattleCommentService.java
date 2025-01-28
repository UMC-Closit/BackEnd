package UMC_7th.Closit.domain.battle.service.battleCommentService;

import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.entity.BattleComment;
import org.springframework.data.domain.Slice;

public interface BattleCommentService {
    BattleComment createBattleComment(Long battleId, BattleCommentRequestDTO.createBattleCommentRequestDTO request); // 배틀 댓글 생성
    Slice<BattleComment> getBattleCommentList(Long battleId, Integer page); // 배틀 댓글 조회
    void deleteBattleComment(Long battleId, Long battleCommentId); // 배틀 댓글 삭제
}