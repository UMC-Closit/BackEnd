package UMC_7th.Closit.domain.follow.dto;

import UMC_7th.Closit.global.validation.annotation.ExistUser;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class FollowRequestDTO {

    @Getter
    public static class CreateFollowDTO {

        @NotNull(message = "팔로워 id는 필수 입력 값입니다.")
        @ExistUser
        private Long follower;

        @NotNull(message = "팔로잉 id는 필수 입력 값입니다.")
        @ExistUser
        private Long following;
    }
}
