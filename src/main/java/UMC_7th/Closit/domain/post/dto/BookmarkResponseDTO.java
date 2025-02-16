package UMC_7th.Closit.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookmarkResponseDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateBookmarkResultDTO { // 북마크 생성
        private String clositId;
        private String userName;
        private Long bookmarkId;
        private Long postId;
        private LocalDateTime createdAt;
    }
}
