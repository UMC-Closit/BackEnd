package UMC_7th.Closit.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class CommentRequestDTO {
    @Getter
    public static class CreateCommentRequestDTO{
        @NotNull
        private Long userId;
        @NotBlank
        private String content;
    }
}
