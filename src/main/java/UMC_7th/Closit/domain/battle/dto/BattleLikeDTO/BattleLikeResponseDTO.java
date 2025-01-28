package UMC_7th.Closit.domain.battle.dto.BattleLikeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class BattleLikeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBattleLikeResultDTO { // 배틀 좋아요 생성
        private Long battleLikeId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BattleLikePreviewDTO { // 배틀 좋아요 조회
        private Long userId;
        private Long battleLikeId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BattleLikePreviewListDTO {
        private List<BattleLikePreviewDTO> battleLikePreviewDTOList;
        private Integer listSize;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
    }
}