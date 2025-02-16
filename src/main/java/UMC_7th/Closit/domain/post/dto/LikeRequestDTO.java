package UMC_7th.Closit.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LikeRequestDTO {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateLikeDTO{
        private Long postId;
        private String clositId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnlikeDTO{
        private Long postId;
        private String clositId;
        private Long likeId;
    }

}
