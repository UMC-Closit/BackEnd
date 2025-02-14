package UMC_7th.Closit.domain.highlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class HighlightResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateHighlightResultDTO {
        private Long highlightId;
        private Long userId;
        private Long postId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HighlightDTO {
        private Long highlightId;
        private String clositId;
        private Long postId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HighlightDetailDTO {
        private Long highlightId;
        private String clositId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private PostInfoDTO post;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostInfoDTO {
        private Long id;
        private String backImage;
        private LocalDateTime createdAt;
    }
}
