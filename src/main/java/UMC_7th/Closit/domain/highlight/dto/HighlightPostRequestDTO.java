package UMC_7th.Closit.domain.highlight.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class HighlightPostRequestDTO {

    @Getter
    public static class CreateHighlightPostDTO {
        @NotNull
        private Long post;
        @NotBlank
        private Long highlight;
    }
}
