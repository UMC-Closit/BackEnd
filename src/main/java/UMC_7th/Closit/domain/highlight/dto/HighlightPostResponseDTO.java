package UMC_7th.Closit.domain.highlight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class HighlightPostResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateHighlightPostResultDTO {
        private Long highlightPostId;
        private LocalDateTime createdAt;
    }
}
