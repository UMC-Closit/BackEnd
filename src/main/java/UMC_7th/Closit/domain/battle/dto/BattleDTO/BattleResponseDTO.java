package UMC_7th.Closit.domain.battle.dto.BattleDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BattleResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBattleResultDTO { // 배틀 생성
        private Long userId;
        private Long battleId;
        private LocalDate deadline;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeBattleResultDTO { // 배틀 신청
        private Long firstUserId;
        private Long secondUserId;
        private Long firstPostId;
        private Long secondPostId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteBattleResultDTO { // 배틀 투표
        private Long voterId;
        private Long battleId;
        private Long firstUserId;
        private Long secondUserId;
        private Integer firstVotingCount;
        private Integer secondVotingCount;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BattlePreviewDTO { // 배틀 게시글 목록 조회
        private Long firstUserId;
        private Long secondUserId;
        private Long firstPostId;
        private Long secondPostId;
        private String title;
        private Integer firstVotingCount;
        private Integer secondVotingCount;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BattlePreviewListDTO {
        private List<BattlePreviewDTO> battlePreviewList;
        private Integer listSize;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeBattlePreviewDTO { // 배틀 챌린지 게시글 목록 조회
        private Long firstUserId;
        private Long firstPostId;
        private String title;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeBattlePreviewListDTO {
        private List<ChallengeBattlePreviewDTO> challengeBattlePreviewList;
        private Integer listSize;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
    }
}