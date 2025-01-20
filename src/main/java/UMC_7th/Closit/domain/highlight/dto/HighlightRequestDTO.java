package UMC_7th.Closit.domain.highlight.dto;

import UMC_7th.Closit.global.validation.annotation.ExistUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class HighlightRequestDTO {

    @Getter
    public static class CreateHighlightDTO {
        @NotNull
        @ExistUser
        private Long user;
        @NotBlank
        private String title;
        @NotBlank
        private String thumbnail;
    }
}
