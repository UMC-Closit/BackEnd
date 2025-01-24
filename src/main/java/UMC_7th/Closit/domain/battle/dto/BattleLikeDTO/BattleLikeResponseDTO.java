package UMC_7th.Closit.domain.battle.dto.BattleLikeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BattleLikeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBattleLikeResultDTO { // 배틀 좋아요 생성
        private Long battleLikeId;
        private LocalDateTime createdAt;
    }
}
