package UMC_7th.Closit.domain.highlight.dto;

import UMC_7th.Closit.global.validation.annotation.ExistHighlight;
import UMC_7th.Closit.global.validation.annotation.ExistPost;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class HighlightPostRequestDTO {

    @Getter
    public static class CreateHighlightPostDTO {
        @NotNull(message = "게시글 id는 필수 입력 값입니다.")
        @ExistPost
        private Long post;
        @ExistHighlight
        @NotBlank(message = "하이라이트 id는 필수 입력 값입니다.")
        private Long highlight;
    }
}
