package UMC_7th.Closit.domain.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class FollowResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateFollowResultDTO {
        private Long followId;
        private Long followerId;
        private Long followingId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FollowDTO {
        private Long followId;
        private Long followerId;
        private Long followingId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
