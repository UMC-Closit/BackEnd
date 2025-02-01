package UMC_7th.Closit.domain.battle.dto.BattleCmtDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class BattleCommentResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createBattleCommentResultDTO { // 배틀 댓글 생성
        private Long battleCommentId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BattleCommentPreviewDTO { // 배틀 댓글 조회
        private Long userId;
        private Long battleCommentId;
        private String content;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BattleCommentPreviewListDTO {
        private List<BattleCommentPreviewDTO> battleCommentPreviewList;
        private Integer listSize;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
    }
}