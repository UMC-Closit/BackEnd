package UMC_7th.Closit.domain.highlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class HighlightResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateHighlightResultDTO {
        private Long highlightId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HighlightDetailDTO {
        private Long highlightId;
        private Long userId;
        private String title;
        private String thumbnail;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<HighlightPostResponseDTO.HighlightPostDTO> highlightPosts;
    }
}
