package UMC_7th.Closit.domain.highlight.dto;

import UMC_7th.Closit.global.validation.annotation.ExistUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class HighlightRequestDTO {

    @Getter
    public static class CreateHighlightDTO {

        @NotNull(message = "사용자 id는 필수 입력 값입니다.")
        @ExistUser
        private Long user;

        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;

        @NotBlank(message = "대표 사진은 필수 입력 값입니다.")
        private String thumbnail;
    }

    @Getter
    public static class UpdateHighlightDTO {

        @NotNull(message = "사용자 id는 필수 입력 값입니다.")
        @ExistUser
        private Long user;

        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;

        @NotBlank(message = "대표 사진은 필수 입력 값입니다.")
        private String thumbnail;
    }
}
