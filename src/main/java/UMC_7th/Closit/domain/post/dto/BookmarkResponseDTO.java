package UMC_7th.Closit.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookmarkResponseDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BookmarkStatusDTO{
        private Long bookmarkId;
        private Long postId;
        private Long userId;
    }
}
