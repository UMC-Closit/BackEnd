package UMC_7th.Closit.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookmarkRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkDTO{
        private Long userId;
        private Long postId;
    }
}
