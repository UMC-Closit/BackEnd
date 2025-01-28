package UMC_7th.Closit.domain.battle.dto.battleCommentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BattleCommentResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createBattleCommentResultDTO { // 배틀 댓글 생성
        private Long battleCommentId;
        private LocalDateTime createdAt;
    }
}
