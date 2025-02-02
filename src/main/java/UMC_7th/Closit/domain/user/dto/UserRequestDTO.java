package UMC_7th.Closit.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

public class UserRequestDTO {

    @Getter
    @AllArgsConstructor
    public static class CreateUserDTO {
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;

        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Size(min = 8, message = "비밀번호는 최소 8자리 이상이어야 합니다.")
        private String password;

        private String clositId;

        private LocalDate birth;

        private String profileImage;
    }
}
