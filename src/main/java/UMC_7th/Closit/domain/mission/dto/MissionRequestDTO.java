package UMC_7th.Closit.domain.mission.dto;

import UMC_7th.Closit.domain.mission.entity.MissionStatus;
import UMC_7th.Closit.global.validation.annotation.EnumValid;
import UMC_7th.Closit.global.validation.annotation.ExistUser;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class CreateMissionDTO {

        @NotNull(message = "사용자 id는 필수 입력 값입니다.")
        @ExistUser
        private Long user;

        @NotNull(message = "미션 상태는 필수 입력 값입니다.")
        @EnumValid(enumClass = MissionStatus.class, message = "유효하지 않은 미션 상태입니다.", ignoreCase = true)
        private String status;
    }

    @Getter
    public static class UpdateMissionDTO {

        @NotNull(message = "미션 상태는 필수 입력 값입니다.")
        @EnumValid(enumClass = MissionStatus.class, message = "유효하지 않은 미션 상태입니다.", ignoreCase = true)
        private String status;
    }
}
