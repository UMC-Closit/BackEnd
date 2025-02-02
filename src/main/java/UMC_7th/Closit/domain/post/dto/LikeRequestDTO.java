package UMC_7th.Closit.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeRequestDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateLikeDTO{
        private Long postId;
        private Long userId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnlikeDTO{
        private Long postId;
        private Long userId;
        private Long likeId;
    }

}
