package UMC_7th.Closit.domain.battle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BattleResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBattleResultDTO { // 배틀 생성
        private Long userId;
        private Long battleId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeBattleResultDTO { // 배틀 신청
        private Long firstPostId;
        private Long secondPostId;
        private LocalDateTime createdAt;
    }
}